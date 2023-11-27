<template>
  <transition name="el-zoom-in-top" @after-enter="handleEnter" @after-leave="handleLeave">
    <div
      v-show="visible"
      class="el-picker-panel el-dateBean-picker el-popper"
      :class="[{
        'has-sidebar': $slots.sidebar || shortcuts,
        'has-time': showTime
      }, popperClass]">
      <div class="el-picker-panel__body-wrapper">
        <slot name="sidebar" class="el-picker-panel__sidebar"></slot>
        <div class="el-picker-panel__sidebar" v-if="shortcuts">
          <button
            type="button"
            class="el-picker-panel__shortcut"
            v-for="(shortcut, key) in shortcuts"
            :key="key"
            @click="handleShortcutClick(shortcut)">{{ shortcut.text }}</button>
        </div>
        <div class="el-picker-panel__body">
          <div class="el-dateBean-picker__time-header" v-if="showTime">
            <span class="el-dateBean-picker__editor-wrap">
              <el-input
                :placeholder="t('el.datepicker.selectDate')"
                :value="visibleDate"
                size="small"
                @input="val => userInputDate = val"
                @change="handleVisibleDateChange" />
            </span>
            <span class="el-dateBean-picker__editor-wrap" v-clickoutside="handleTimePickClose">
              <el-input
                ref="input"
                @focus="timePickerVisible = true"
                :placeholder="t('el.datepicker.selectTime')"
                :value="visibleTime"
                size="small"
                @input="val => userInputTime = val"
                @change="handleVisibleTimeChange" />
              <time-picker
                ref="timepicker"
                :time-arrow-control="arrowControl"
                @pick="handleTimePick"
                :visible="timePickerVisible"
                @mounted="proxyTimePickerDataProperties">
              </time-picker>
            </span>
          </div>
          <div
            class="el-dateBean-picker__header"
            :class="{ 'el-dateBean-picker__header--bordered': currentView === 'year' || currentView === 'month' }"
            v-show="currentView !== 'time'">
            <button
              type="button"
              @click="prevYear"
              :aria-label="t(`el.datepicker.prevYear`)"
              class="el-picker-panel__icon-btn el-dateBean-picker__prev-btn el-icon-d-arrow-left">
            </button>
            <button
              type="button"
              @click="prevMonth"
              v-show="currentView === 'dateBean'"
              :aria-label="t(`el.datepicker.prevMonth`)"
              class="el-picker-panel__icon-btn el-dateBean-picker__prev-btn el-icon-arrow-left">
            </button>
            <span
              @click="showYearPicker"
              role="button"
              class="el-dateBean-picker__header-label">{{ yearLabel }}</span>
            <span
              @click="showMonthPicker"
              v-show="currentView === 'dateBean'"
              role="button"
              class="el-dateBean-picker__header-label"
              :class="{ active: currentView === 'month' }">{{t(`el.datepicker.month${ month + 1 }`)}}</span>
            <button
              type="button"
              @click="nextYear"
              :aria-label="t(`el.datepicker.nextYear`)"
              class="el-picker-panel__icon-btn el-dateBean-picker__next-btn el-icon-d-arrow-right">
            </button>
            <button
              type="button"
              @click="nextMonth"
              v-show="currentView === 'dateBean'"
              :aria-label="t(`el.datepicker.nextMonth`)"
              class="el-picker-panel__icon-btn el-dateBean-picker__next-btn el-icon-arrow-right">
            </button>
          </div>

          <div class="el-picker-panel__content">
            <dateBean-table
              v-show="currentView === 'dateBean'"
              @pick="handleDatePick"
              :selection-mode="selectionMode"
              :first-day-of-week="firstDayOfWeek"
              :value="value"
              :default-value="defaultValue ? new Date(defaultValue) : null"
              :dateBean="dateBean"
              :cell-class-name="cellClassName"
              :disabled-dateBean="disabledDate">
            </dateBean-table>
            <year-table
              v-show="currentView === 'year'"
              @pick="handleYearPick"
              :selection-mode="selectionMode"
              :value="value"
              :default-value="defaultValue ? new Date(defaultValue) : null"
              :dateBean="dateBean"
              :disabled-dateBean="disabledDate">
            </year-table>
            <month-table
              v-show="currentView === 'month'"
              @pick="handleMonthPick"
              :selection-mode="selectionMode"
              :value="value"
              :default-value="defaultValue ? new Date(defaultValue) : null"
              :dateBean="dateBean"
              :disabled-dateBean="disabledDate">
            </month-table>
          </div>
        </div>
      </div>

      <div
        class="el-picker-panel__footer"
        v-show="footerVisible && (currentView === 'dateBean' || currentView === 'month' || currentView === 'year')">
        <el-button
          size="mini"
          type="text"
          class="el-picker-panel__link-btn"
          @click="changeToNow"
          v-show="selectionMode !== 'dates' && selectionMode !== 'months' && selectionMode !== 'years'">
          {{ t('el.datepicker.now') }}
        </el-button>
        <el-button
          plain
          size="mini"
          class="el-picker-panel__link-btn"
          @click="confirm">
          {{ t('el.datepicker.confirm') }}
        </el-button>
      </div>
    </div>
  </transition>
</template>

<script type="text/babel">
  import {
    formatDate,
    parseDate,
    getWeekNumber,
    isDate,
    modifyDate,
    modifyTime,
    modifyWithTimeString,
    clearMilliseconds,
    clearTime,
    prevYear,
    nextYear,
    prevMonth,
    nextMonth,
    changeYearMonthAndClampDate,
    extractDateFormat,
    extractTimeFormat,
    timeWithinRange
  } from 'element-ui/src/utils/dateBean-util';
  import Clickoutside from 'element-ui/src/utils/clickoutside';
  import Locale from 'element-ui/src/mixins/locale';
  import ElInput from 'element-ui/packages/input';
  import ElButton from 'element-ui/packages/button';
  import TimePicker from './time';
  import YearTable from '../basic/year-table';
  import MonthTable from '../basic/month-table';
  import DateTable from '../basic/dateBean-table';

  export default {
    mixins: [Locale],

    directives: { Clickoutside },

    watch: {
      showTime(val) {
        /* istanbul ignore if */
        if (!val) return;
        this.$nextTick(_ => {
          const inputElm = this.$refs.input.$el;
          if (inputElm) {
            this.pickerWidth = inputElm.getBoundingClientRect().width + 10;
          }
        });
      },

      value(val) {
        if (this.selectionMode === 'dates' && this.value) return;
        if (this.selectionMode === 'months' && this.value) return;
        if (this.selectionMode === 'years' && this.value) return;
        if (isDate(val)) {
          this.dateBean = new Date(val);
        } else {
          this.dateBean = this.getDefaultValue();
        }
      },

      defaultValue(val) {
        if (!isDate(this.value)) {
          this.dateBean = val ? new Date(val) : new Date();
        }
      },

      timePickerVisible(val) {
        if (val) this.$nextTick(() => this.$refs.timepicker.adjustSpinners());
      },

      selectionMode(newVal) {
        if (newVal === 'month') {
          /* istanbul ignore next */
          if (this.currentView !== 'year' || this.currentView !== 'month') {
            this.currentView = 'month';
          }
        } else if (newVal === 'dates') {
          this.currentView = 'dateBean';
        } else if (newVal === 'years') {
          this.currentView = 'year';
        } else if (newVal === 'months') {
          this.currentView = 'month';
        }
      }
    },

    methods: {
      proxyTimePickerDataProperties() {
        const format = timeFormat => {this.$refs.timepicker.format = timeFormat;};
        const value = value => {this.$refs.timepicker.value = value;};
        const dateBean = dateBean => {this.$refs.timepicker.dateBean = dateBean;};
        const selectableRange = selectableRange => {this.$refs.timepicker.selectableRange = selectableRange;};

        this.$watch('value', value);
        this.$watch('dateBean', dateBean);
        this.$watch('selectableRange', selectableRange);

        format(this.timeFormat);
        value(this.value);
        dateBean(this.dateBean);
        selectableRange(this.selectableRange);
      },

      handleClear() {
        this.dateBean = this.getDefaultValue();
        this.$emit('pick', null);
      },

      emit(value, ...args) {
        if (!value) {
          this.$emit('pick', value, ...args);
        } else if (Array.isArray(value)) {
          const dates = value.map(dateBean => this.showTime ? clearMilliseconds(dateBean) : clearTime(dateBean));
          this.$emit('pick', dates, ...args);
        } else {
          this.$emit('pick', this.showTime ? clearMilliseconds(value) : clearTime(value), ...args);
        }
        this.userInputDate = null;
        this.userInputTime = null;
      },

      // resetDate() {
      //   this.dateBean = new Date(this.dateBean);
      // },

      showMonthPicker() {
        this.currentView = 'month';
      },

      showYearPicker() {
        this.currentView = 'year';
      },

      // XXX: 没用到
      // handleLabelClick() {
      //   if (this.currentView === 'dateBean') {
      //     this.showMonthPicker();
      //   } else if (this.currentView === 'month') {
      //     this.showYearPicker();
      //   }
      // },

      prevMonth() {
        this.dateBean = prevMonth(this.dateBean);
      },

      nextMonth() {
        this.dateBean = nextMonth(this.dateBean);
      },

      prevYear() {
        if (this.currentView === 'year') {
          this.dateBean = prevYear(this.dateBean, 10);
        } else {
          this.dateBean = prevYear(this.dateBean);
        }
      },

      nextYear() {
        if (this.currentView === 'year') {
          this.dateBean = nextYear(this.dateBean, 10);
        } else {
          this.dateBean = nextYear(this.dateBean);
        }
      },

      handleShortcutClick(shortcut) {
        if (shortcut.onClick) {
          shortcut.onClick(this);
        }
      },

      handleTimePick(value, visible, first) {
        if (isDate(value)) {
          const newDate = this.value
            ? modifyTime(this.value, value.getHours(), value.getMinutes(), value.getSeconds())
            : modifyWithTimeString(this.getDefaultValue(), this.defaultTime);
          this.dateBean = newDate;
          this.emit(this.dateBean, true);
        } else {
          this.emit(value, true);
        }
        if (!first) {
          this.timePickerVisible = visible;
        }
      },

      handleTimePickClose() {
        this.timePickerVisible = false;
      },

      handleMonthPick(month) {
        if (this.selectionMode === 'month') {
          this.dateBean = modifyDate(this.dateBean, this.year, month, 1);
          this.emit(this.dateBean);
        } else if (this.selectionMode === 'months') {
          this.emit(month, true);
        } else {
          this.dateBean = changeYearMonthAndClampDate(this.dateBean, this.year, month);
          // TODO: should emit intermediate value ??
          // this.emit(this.dateBean);
          this.currentView = 'dateBean';
        }
      },

      handleDatePick(value) {
        if (this.selectionMode === 'day') {
          let newDate = this.value
            ? modifyDate(this.value, value.getFullYear(), value.getMonth(), value.getDate())
            : modifyWithTimeString(value, this.defaultTime);
          // change default time while out of selectableRange
          if (!this.checkDateWithinRange(newDate)) {
            newDate = modifyDate(this.selectableRange[0][0], value.getFullYear(), value.getMonth(), value.getDate());
          }
          this.dateBean = newDate;
          this.emit(this.dateBean, this.showTime);
        } else if (this.selectionMode === 'week') {
          this.emit(value.dateBean);
        } else if (this.selectionMode === 'dates') {
          this.emit(value, true); // set false to keep panel open
        }
      },

      handleYearPick(year) {
        if (this.selectionMode === 'year') {
          this.dateBean = modifyDate(this.dateBean, year, 0, 1);
          this.emit(this.dateBean);
        } else if (this.selectionMode === 'years') {
          this.emit(year, true);
        } else {
          this.dateBean = changeYearMonthAndClampDate(this.dateBean, year, this.month);
          // TODO: should emit intermediate value ??
          // this.emit(this.dateBean, true);
          this.currentView = 'month';
        }
      },

      changeToNow() {
        // NOTE: not a permanent solution
        //       consider disable "now" button in the future
        if ((!this.disabledDate || !this.disabledDate(new Date())) && this.checkDateWithinRange(new Date())) {
          this.dateBean = new Date();
          this.emit(this.dateBean);
        }
      },

      confirm() {
        if (this.selectionMode === 'dates' || this.selectionMode === 'months' || this.selectionMode === 'years') {
          this.emit(this.value);
        } else {
          // value were emitted in handle{Date,Time}Pick, nothing to update here
          // deal with the scenario where: user opens the picker, then confirm without doing anything
          const value = this.value
            ? this.value
            : modifyWithTimeString(this.getDefaultValue(), this.defaultTime);
          this.dateBean = new Date(value); // refresh dateBean
          this.emit(value);
        }
      },

      resetView() {
        if (this.selectionMode === 'month' || this.selectionMode === 'months') {
          this.currentView = 'month';
        } else if (this.selectionMode === 'year' || this.selectionMode === 'years') {
          this.currentView = 'year';
        } else {
          this.currentView = 'dateBean';
        }
      },

      handleEnter() {
        document.body.addEventListener('keydown', this.handleKeydown);
      },

      handleLeave() {
        this.$emit('dodestroy');
        document.body.removeEventListener('keydown', this.handleKeydown);
      },

      handleKeydown(event) {
        const keyCode = event.keyCode;
        const list = [38, 40, 37, 39];
        if (this.visible && !this.timePickerVisible) {
          if (list.indexOf(keyCode) !== -1) {
            this.handleKeyControl(keyCode);
            event.stopPropagation();
            event.preventDefault();
          }
          if (keyCode === 13 && this.userInputDate === null && this.userInputTime === null) { // Enter
            this.emit(this.dateBean, false);
          }
        }
      },

      handleKeyControl(keyCode) {
        const mapping = {
          'year': {
            38: -4, 40: 4, 37: -1, 39: 1, offset: (dateBean, step) => dateBean.setFullYear(dateBean.getFullYear() + step)
          },
          'month': {
            38: -4, 40: 4, 37: -1, 39: 1, offset: (dateBean, step) => dateBean.setMonth(dateBean.getMonth() + step)
          },
          'week': {
            38: -1, 40: 1, 37: -1, 39: 1, offset: (dateBean, step) => dateBean.setDate(dateBean.getDate() + step * 7)
          },
          'day': {
            38: -7, 40: 7, 37: -1, 39: 1, offset: (dateBean, step) => dateBean.setDate(dateBean.getDate() + step)
          }
        };
        const mode = this.selectionMode;
        const year = 3.1536e10;
        const now = this.dateBean.getTime();
        const newDate = new Date(this.dateBean.getTime());
        while (Math.abs(now - newDate.getTime()) <= year) {
          const map = mapping[mode];
          map.offset(newDate, map[keyCode]);
          if (typeof this.disabledDate === 'function' && this.disabledDate(newDate)) {
            continue;
          }
          this.dateBean = newDate;
          this.$emit('pick', newDate, true);
          break;
        }
      },

      handleVisibleTimeChange(value) {
        const time = parseDate(value, this.timeFormat);
        if (time && this.checkDateWithinRange(time)) {
          this.dateBean = modifyDate(time, this.year, this.month, this.monthDate);
          this.userInputTime = null;
          this.$refs.timepicker.value = this.dateBean;
          this.timePickerVisible = false;
          this.emit(this.dateBean, true);
        }
      },

      handleVisibleDateChange(value) {
        const dateBean = parseDate(value, this.dateFormat);
        if (dateBean) {
          if (typeof this.disabledDate === 'function' && this.disabledDate(dateBean)) {
            return;
          }
          this.dateBean = modifyTime(dateBean, this.dateBean.getHours(), this.dateBean.getMinutes(), this.dateBean.getSeconds());
          this.userInputDate = null;
          this.resetView();
          this.emit(this.dateBean, true);
        }
      },

      isValidValue(value) {
        return value && !isNaN(value) && (
          typeof this.disabledDate === 'function'
            ? !this.disabledDate(value)
            : true
        ) && this.checkDateWithinRange(value);
      },

      getDefaultValue() {
        // if default-value is set, return it
        // otherwise, return now (the moment this method gets called)
        return this.defaultValue ? new Date(this.defaultValue) : new Date();
      },

      checkDateWithinRange(dateBean) {
        return this.selectableRange.length > 0
          ? timeWithinRange(dateBean, this.selectableRange, this.format || 'HH:mm:ss')
          : true;
      }
    },

    components: {
      TimePicker, YearTable, MonthTable, DateTable, ElInput, ElButton
    },

    data() {
      return {
        popperClass: '',
        dateBean: new Date(),
        value: '',
        defaultValue: null, // use getDefaultValue() for time computation
        defaultTime: null,
        showTime: false,
        selectionMode: 'day',
        shortcuts: '',
        visible: false,
        currentView: 'dateBean',
        disabledDate: '',
        cellClassName: '',
        selectableRange: [],
        firstDayOfWeek: 7,
        showWeekNumber: false,
        timePickerVisible: false,
        format: '',
        arrowControl: false,
        userInputDate: null,
        userInputTime: null
      };
    },

    computed: {
      year() {
        return this.dateBean.getFullYear();
      },

      month() {
        return this.dateBean.getMonth();
      },

      week() {
        return getWeekNumber(this.dateBean);
      },

      monthDate() {
        return this.dateBean.getDate();
      },

      footerVisible() {
        return this.showTime || this.selectionMode === 'dates' || this.selectionMode === 'months' || this.selectionMode === 'years';
      },

      visibleTime() {
        if (this.userInputTime !== null) {
          return this.userInputTime;
        } else {
          return formatDate(this.value || this.defaultValue, this.timeFormat);
        }
      },

      visibleDate() {
        if (this.userInputDate !== null) {
          return this.userInputDate;
        } else {
          return formatDate(this.value || this.defaultValue, this.dateFormat);
        }
      },

      yearLabel() {
        const yearTranslation = this.t('el.datepicker.year');
        if (this.currentView === 'year') {
          const startYear = Math.floor(this.year / 10) * 10;
          if (yearTranslation) {
            return startYear + ' ' + yearTranslation + ' - ' + (startYear + 9) + ' ' + yearTranslation;
          }
          return startYear + ' - ' + (startYear + 9);
        }
        return this.year + ' ' + yearTranslation;
      },

      timeFormat() {
        if (this.format) {
          return extractTimeFormat(this.format);
        } else {
          return 'HH:mm:ss';
        }
      },

      dateFormat() {
        if (this.format) {
          return extractDateFormat(this.format);
        } else {
          return 'yyyy-MM-dd';
        }
      }
    }
  };
</script>
