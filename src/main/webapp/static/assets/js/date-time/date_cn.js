var dates = $.fn.datepicker.dates;
dates['cn']={
		days: ["周日", "周一", "周二", "周三", "周四", "周五", "周六", "周日"],
		daysShort: ["日", "一", "二", "三", "四", "五", "六", "七"],
		daysMin: ["日", "一", "二", "三", "四", "五", "六", "七"],
		months: ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],
		monthsShort: ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],
		today: "今天",
		clear: "清除"
	};
 var defaults = $.fn.datepicker.defaults = {
			autoclose: false,
			beforeShowDay: $.noop,
			calendarWeeks: false,
			clearBtn: false,
			daysOfWeekDisabled: [],
			endDate: Infinity,
			forceParse: true,
			format: 'yyyy-mm-dd',
			keyboardNavigation: true,
			language: 'cn',
			minViewMode: 0,
			orientation: "auto",
			rtl: false,
			startDate: -Infinity,
			startView: 0,
			todayBtn: false,
			todayHighlight: false,
			weekStart: 0
		};