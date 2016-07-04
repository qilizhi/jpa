(function (factory) {
    'use strict';
    if (typeof define === 'function' && (define.amd || define.cmd)) {
        // Register as an anonymous AMD or CMD module:
        define(['jquery'], factory);
    } else if (typeof define === "function" && define.cmd) {
        // Register as an anonymous CMD module:
        define(factory);
    } else {
        // Browser globals:
        factory(window.jQuery);
    }
}(function(require){
	var jQuery = require;
	if (require && typeof require.async === "function" && define.cmd){
		jQuery = require("jquery");
	}
   (function($) {
     $.fn.extend({
       priceCalendar: function(s) {         
         s = $.extend({}, $.fn.params, s);
         return this.each(function() {         
         var obj = $(this);
         var jsonDict = {};
//         var year = new Date().getFullYear()||s.year;
//         var month = new Date().getMonth()||s.month;
//         var date = new Date().getDate()||s.date;
         var currentDate = s.startTime;
        
         var nextMonthButton;
         var prevMonthButton;
         var removeRowNums = 0;
         var removeRowNumsAtTitle = 0;
         var rowNum = 6;
         var slideHeight = 0;
         var firstSlideHeight = 0;
         var index = 0;
         var isFirstClick = true;
         var isInput = obj.get(0).nodeName.toLowerCase() == "input" ? true : false;
         minPriceByMonth = {};

         //获取JS路径
         var getJPath = function() {
           var js = document.scripts;
           var jsPath = "";
           for (var i = js.length; i > 0; i--) {
             if (js[i - 1].src.indexOf("price-calendar.js") > -1) {
               jsPath = js[i - 1].src.substring(0, js[i - 1].src.lastIndexOf("/") + 1);
             }
           }
           return jsPath;
         };

         //皮肤初始化
         var loadSkin = function(skin) {
           $("<link>")
             .attr({
               id:"price-calendar-link-id",
               rel: "stylesheet",
               type: "text/css",
               href: getJPath() + "skin/" + skin + "/PriceCalendar.css"
             }).appendTo("head");
         };

         //判断是否已经加载了样式
         if($("#price-calendar-link-id").length <= 0)
        	 loadSkin(s.skin);

         //[{"lineDate":"2014-04-01","minPrice":34.00}]
         if (s.json != null) {
           for (var i = 0; i < s.json.length; i++) {
        	   jsonDict[s.json[i].tuanDate]=s.parseResult(s.json[i]);//自定义解析方式
        	   if(jsonDict[s.json[i].tuanDate]==null){//默认解析方式
        		   jsonDict[s.json[i].tuanDate] = {
        				"dayNo": s.json[i].lineDate,
        				"minPrice": s.json[i].adultPrice==null?"":s.json[i].adultPrice,//s.json[i].mlxPrice, 日历显示成人价
        				"cPrice":s.json[i].adultPrice==null?"":s.json[i].adultPrice ,
        				"ePrice":s.json[i].childPrice==null?"":s.json[i].childPrice,
        				"id":s.json[i].id==null?"0":s.json[i].id,
        				"routeId":s.json[i].goodsNo==null?"0":s.json[i].goodsNo,
        				"roomCount":s.json[i].roomCount==null?"":s.json[i].roomCount,
        				"roomDiffPrice":s.json[i].roomDiffPrice==null?"":s.json[i].roomDiffPrice,
        				"safePrice":s.json[i].safePrice==null?"":s.json[i].safePrice,
        				"visaPrice":s.json[i].visaPrice==null?"":s.json[i].visaPrice,
        				"num":s.json[i].num==null?"0":s.json[i].num,
        				"tuanNo":s.json[i].tuanNo==null?"0":s.json[i].tuanNo		
        		   };
        	   }
             var day = new Date(Date.parse(s.json[i].tuanDate.replace(/-/g, "/")));
             if (!(day.asString("yyyy-mm") in minPriceByMonth))
               minPriceByMonth[day.asString("yyyy-mm")] = s.json[i].minPrice;
             else {
               if (minPriceByMonth[day.asString("yyyy-mm")] > s.json[i].minPrice) {
                 minPriceByMonth[day.asString("yyyy-mm")] = s.json[i].minPrice;
               }
             }
           }
         }
        // console.dir(jsonDict);
         //获取需要移除的行数
         var removeRowNum = function() {
           var removeRowNum = 0;
           var today = new Date();
           var tdNum = today.getDate() + new Date(today.getFullYear(), today.getMonth(), 1).getDay() - 1; //今天所在td的index
           removeRowNum = Math.floor(tdNum / 7);
           removeRowNum = removeRowNum > 4 ? 4 : removeRowNum; //删除的行数不能大于两行(剩余行数不能少于两行)
           return removeRowNum;
         };

         var creatTd = function(date, minPrice,jsonObj) {
//console.log(minPrice);
//console.log(jsonObj);
           if (date == null && minPrice == null) {
             return "<td class='noneData'></td>";
           }
           var dateResult = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
           var dateWeek = date.getDay();
           var dateshort = date.asString("yyyy-mm-dd");
           if(s.useEditModel){
        	   minPrice = minPrice && minPrice.toString() === "0" ? "" : (minPrice ? minPrice : "");
        	   minPrice = minPrice == -1 ? "" : minPrice;
        	   var $tdAttrs = s.tdAttrs(jsonObj); 
               return "<td class='td lowest' "+$tdAttrs+" minPrice='" + minPrice + "' data-full-date='" + dateshort + "' data-date='" + dateResult + "' data-week='" + dateWeek + "'><span class='date'>" + date.getDate() + "</span><span class='price'><dfn>¥</dfn>" + minPrice + "</span></td>";
           }
           if (minPrice == null) {
             return "<td class='noneData'><span class='date'>" + dateResult + "</span><span>--</span></td>";
           }
           if (minPrice < 0) {
             return "<td class='td nonePrice' style='text-align:center' data-date='" + dateResult + "'><span class='date'>" + date.getDate() + "</span><span>"+s.defaultText+"</span></td>";
           }
           if (minPrice == minPriceByMonth[date.asString("yyyy-mm")]) {
             return "<td class='td lowest' minPrice='" + minPrice + "' data-date='" + dateResult + "'><span class='date'>" + date.getDate() + "</span><span class='price'><dfn>¥</dfn>" + minPrice + "</span></td>";
           }
           
           return "<td class='td' minPrice='" + minPrice + "' data-date='" + dateResult + "'><span class='date'>" + date.getDate() + "</span><span class='price'><dfn>¥</dfn>" + minPrice + "</span></td>";
         };

         obj.empty();

         //模板
         var temple = [
           "<div class='month'></div>",
           "<h4 class='clearfix'><span>SUN日</span><span>MON一</span><span>TUE二</span><span>WED三</span><span>THU四</span><span>FRI五</span><span>SAT六</span></h4>"
         ];
         //初始化
         var currentCalenderPanel = $('<div />', {
           "class": 'lowprice_box'
         });

         if(isInput){
            obj.after(currentCalenderPanel);
         } else {
            obj.append(currentCalenderPanel);
         }

         for (var i = 0; i < temple.length; i++) {
           currentCalenderPanel.append(temple[i]);
         }

         prevMonthButton = $("<a />", {
           id: 'CalendarPrev',
           "class": 'prev prev_disable',
           href: 'javascript:;'
         }).appendTo($(".month", currentCalenderPanel));

         nextMonthButton = $("<a />", {
           id: 'CalendarNext',
           "class": 'next',
           href: 'javascript:;'
         }).appendTo($(".month", currentCalenderPanel));

         if (s.showMonthNum < 2) {
           nextMonthButton.addClass("next_disable");
         }

         var tablePanel = $('<div />').appendTo(currentCalenderPanel);

         var tableCalendar = $('<table />', {
           id: 'tableCalendar',
           "class": 'calendar'
         }).appendTo(tablePanel).attr('collapse', 0);
         
         removeRowNums = removeRowNum();
         removeRowNumsAtTitle = removeRowNums;

         //生成日历表格
         for (var i = 0; i < s.showMonthNum; i++) {
           var tableCurrentDate = new Date(currentDate.getFullYear(), currentDate.getMonth() + i, 1);
           var tableCurrentMonth = tableCurrentDate.getMonth() + 1;
           var todayIndexOfTd = currentDate.getDate() + tableCurrentDate.getDay() - 1;
           var tableFirstDateIndex = tableCurrentDate.getDay();
           var tableLastDateIndex = tableCurrentDate.getDaysInMonth() + tableFirstDateIndex - 1;
           var monthWordIndex = 1;
           if(tableLastDateIndex < 35) {
          	 removeRowNums++;
           }
           if (i == 0 && removeRowNums > 2) {
             monthWordIndex = removeRowNumsAtTitle + 1;
             tableCurrentDate.addDays(removeRowNumsAtTitle * 7 - tableFirstDateIndex);
           }

           for (var j = 0; j < rowNum; j++) {
             if (i == 0 && j < removeRowNumsAtTitle && removeRowNums > 2) {
               continue;
             }
             var tableTrHtml = "<tr data-key='" + i + "' data-m='" + tableCurrentDate.asString("yyyy-mm") + "'>";
             if (j == monthWordIndex) {
               tableTrHtml += "<th class='calendarThPadding'><span>" + tableCurrentDate.getFullYear() + "年</span>" + tableCurrentMonth + "月</th>";
             } else {
               tableTrHtml += "<th></th>";
             }
             for (var z = 0; z < 7; z++) {
               var tdIndex = j * 7 + z;
               if (tdIndex < tableFirstDateIndex) {
                 tableTrHtml += creatTd(null , null,null);
                 continue;
               }
               if (tdIndex >= tableFirstDateIndex && tdIndex <= tableLastDateIndex) {
        		 if (i == 0 && tdIndex < todayIndexOfTd) {
        			 tableTrHtml += creatTd(null, null,null);
        		 } else {
    				 var minPrice = jsonDict[tableCurrentDate.asString("yyyy-mm-dd")] ? jsonDict[tableCurrentDate.asString("yyyy-mm-dd")].minPrice : -1;
    				 tableTrHtml += creatTd(tableCurrentDate, minPrice,jsonDict[tableCurrentDate.asString("yyyy-mm-dd")]);
        		 }
        		 tableCurrentDate.addDays(1);
        		 continue;
               }
               if (tdIndex > tableLastDateIndex) {
                 tableTrHtml += creatTd(null, null,null);
                 continue;
               }
             }
             tableTrHtml += "</tr>";
             if((removeRowNums > 2 && removeRowNums > removeRowNumsAtTitle) && j == 5 && i == 0) {
            	 tableTrHtml = "";
             }
             tableCalendar.append(tableTrHtml);
           }
         }


         if(isInput) {
          $(currentCalenderPanel).hide();
          obj.click(function() {
            currentCalenderPanel.show();
          });

          currentCalenderPanel.hover(function(){
            currentCalenderPanel.show();
          },
          function() {
            currentCalenderPanel.hide();
          });
          currentCalenderPanel.css({
               position: 'absolute'/*,
               left: obj.get(0).offsetLeft,
               top: obj.get(0).offsetTop,*/
             });
         }

         $(".td", tableCalendar).click(function() {
            var date = $(this).attr('data-date');
            var month = $(this).parent().attr('data-m');
            var price = $(this).attr('minprice');
            var cprice = "" ;
            var eprice = "" ;
            if(jsonDict && jsonDict.length > 0){
              cprice = jsonDict[month + "-" + date].cPrice ;//成人
              eprice = jsonDict[month + "-" + date].ePrice ;//儿童
            }
            if(isInput){
              obj.val(month + "-" + date);
              currentCalenderPanel.hide();
            }
            s.tdClick(currentCalenderPanel,month + "-" + date,price,cprice,eprice,this);
         });

         $("tr", tableCalendar).hide();
         $("tr:lt("+ rowNum +")", tableCalendar).show();

         var reset = function(){//each兼容IE写法($(selector).each(function)的方法IE不能获取到$(this)指针)
            $.each($("tr[data-key='0']", tableCalendar),function(i,item) {
               firstSlideHeight += $(item).height();
            });
            $.each($("tr[data-key='1']", tableCalendar),function(i,item) {
               slideHeight += $(item).height();
            });

            tablePanel.css({
               height: slideHeight,
               overflow: "hidden"
            });
            $("tr", tableCalendar).show();
         };
         //初始化表格CSS
         tablePanel.css({
           width: "100%",
           marginTop: 0
         });

         //上月下月按钮点击事件初始化
         prevMonthButton.click(function() {
          if(isFirstClick){
            reset();
            isFirstClick = false;
          }
            
           if (index > 0) {
             if (index == 1) {
               tableCalendar.animate({
                 marginTop: '+=' + firstSlideHeight
               }, 400);
             } else {
               tableCalendar.animate({
                 marginTop: '+=' + slideHeight
               }, 400);
             }
             index--;
             if (nextMonthButton.hasClass('next_disable')) {
               nextMonthButton.removeClass('next_disable');
             }
             if (index <= 0) {
               $(this).addClass('prev_disable');
             }
             s.prevMonthButtonClick();
           }
         });

         nextMonthButton.click(function() {
            if(isFirstClick){
              reset();
              isFirstClick = false;
            }
           if (index < s.showMonthNum - 1) {
             if (index == 0) {
               tableCalendar.animate({
                 marginTop: '-=' + firstSlideHeight
               }, 400);
             } else {
               tableCalendar.animate({
                 marginTop: '-=' + slideHeight
               }, 400);
             }
             index++;
             if (prevMonthButton.hasClass('prev_disable')) {
               prevMonthButton.removeClass('prev_disable');
             }
             if (index > s.showMonthNum - 2) {
               $(this).addClass('next_disable');
             }
             s.nextMonthButtonClick();
           }
         });
         });
       }    
 });

     $.fn.params = {
    	//TODO
       showMonthNum: 5, //日历显示月份
       startTime:new Date(),//开始时间
       skin: "default", //皮肤
       json: null, //传递过来的json
       defaultText:"点击查询",//设置默认显示的文字
       useEditModel:false,//是否使用编辑模式
       tdAttrs:function(jsonObj){},//对td属性进行拓展
       parseResult:function(result){return null;},//根据返回数据不同进行解析
       tdClick: function(obj,date,price,cprice , eprice,currTdObj) {},
       contentUpdateEvents:function(){},
       prevMonthButtonClick:function(){},
       nextMonthButtonClick:function(){}
     };
   })(jQuery);


 /*
  * Date prototype extensions. Doesn't depend on any
  * other code. Doens't overwrite existing methods.
  *
  * Adds dayNames, abbrDayNames, monthNames and abbrMonthNames static properties and isLeapYear,
  * isWeekend, isWeekDay, getDaysInMonth, getDayName, getMonthName, getDayOfYear, getWeekOfYear,
  * setDayOfYear, addYears, addMonths, addDays, addHours, addMinutes, addSeconds methods
  *
  * Copyright (c) 2006 Jörn Zaefferer and Brandon Aaron (brandon.aaron@gmail.com || http://brandonaaron.net)
  *
  * Additional methods and properties added by Kelvin Luck: firstDayOfWeek, dateFormat, zeroTime, asString, fromString -
  * I've added my name to these methods so you know who to blame if they are broken!
  *
  * Dual licensed under the MIT and GPL licenses:
  *   http://www.opensource.org/licenses/mit-license.php
  *   http://www.gnu.org/licenses/gpl.html
  *
  */

 /**
  * An Array of day names starting with Sunday.
  *
  * @example dayNames[0]
  * @result 'Sunday'
  *
  * @name dayNames
  * @type Array
  * @cat Plugins/Methods/Date
  */
 Date.dayNames = ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday'];

 /**
  * An Array of abbreviated day names starting with Sun.
  *
  * @example abbrDayNames[0]
  * @result 'Sun'
  *
  * @name abbrDayNames
  * @type Array
  * @cat Plugins/Methods/Date
  */
 Date.abbrDayNames = ['Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat'];

 /**
  * An Array of month names starting with Janurary.
  *
  * @example monthNames[0]
  * @result 'January'
  *
  * @name monthNames
  * @type Array
  * @cat Plugins/Methods/Date
  */
 Date.monthNames = ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'];

 /**
  * An Array of abbreviated month names starting with Jan.
  *
  * @example abbrMonthNames[0]
  * @result 'Jan'
  *
  * @name monthNames
  * @type Array
  * @cat Plugins/Methods/Date
  */
 Date.abbrMonthNames = ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'];

 /**
  * The first day of the week for this locale.
  *
  * @name firstDayOfWeek
  * @type Number
  * @cat Plugins/Methods/Date
  * @author Kelvin Luck
  */
 Date.firstDayOfWeek = 1;

 /**
  * The format that string dates should be represented as (e.g. 'dd/mm/yyyy' for UK, 'mm/dd/yyyy' for US, 'yyyy-mm-dd' for Unicode etc).
  *
  * @name format
  * @type String
  * @cat Plugins/Methods/Date
  * @author Kelvin Luck
  */
 Date.format = 'dd/mm/yyyy';
 //Date.format = 'mm/dd/yyyy';
 //Date.format = 'yyyy-mm-dd';
 //Date.format = 'dd mmm yy';

 /**
  * The first two numbers in the century to be used when decoding a two digit year. Since a two digit year is ambiguous (and date.setYear
  * only works with numbers < 99 and so doesn't allow you to set years after 2000) we need to use this to disambiguate the two digit year codes.
  *
  * @name format
  * @type String
  * @cat Plugins/Methods/Date
  * @author Kelvin Luck
  */
 Date.fullYearStart = '20';

 (function() {

   /**
    * Adds a given method under the given name
    * to the Date prototype if it doesn't
    * currently exist.
    *
    * @private
    */
   function add(name, method) {
     if (!Date.prototype[name]) {
       Date.prototype[name] = method;
     }
   };

   /**
    * Checks if the year is a leap year.
    *
    * @example var dtm = new Date("01/12/2008");
    * dtm.isLeapYear();
    * @result true
    *
    * @name isLeapYear
    * @type Boolean
    * @cat Plugins/Methods/Date
    */
   add("isLeapYear", function() {
     var y = this.getFullYear();
     return (y % 4 == 0 && y % 100 != 0) || y % 400 == 0;
   });

   /**
    * Checks if the day is a weekend day (Sat or Sun).
    *
    * @example var dtm = new Date("01/12/2008");
    * dtm.isWeekend();
    * @result false
    *
    * @name isWeekend
    * @type Boolean
    * @cat Plugins/Methods/Date
    */
   add("isWeekend", function() {
     return this.getDay() == 0 || this.getDay() == 6;
   });

   /**
    * Check if the day is a day of the week (Mon-Fri)
    *
    * @example var dtm = new Date("01/12/2008");
    * dtm.isWeekDay();
    * @result false
    *
    * @name isWeekDay
    * @type Boolean
    * @cat Plugins/Methods/Date
    */
   add("isWeekDay", function() {
     return !this.isWeekend();
   });

   /**
    * Gets the number of days in the month.
    *
    * @example var dtm = new Date("01/12/2008");
    * dtm.getDaysInMonth();
    * @result 31
    *
    * @name getDaysInMonth
    * @type Number
    * @cat Plugins/Methods/Date
    */
   add("getDaysInMonth", function() {
     return [31, (this.isLeapYear() ? 29 : 28), 31, 30, 31, 30, 31, 31, 30, 31, 30, 31][this.getMonth()];
   });

   /**
    * Gets the name of the day.
    *
    * @example var dtm = new Date("01/12/2008");
    * dtm.getDayName();
    * @result 'Saturday'
    *
    * @example var dtm = new Date("01/12/2008");
    * dtm.getDayName(true);
    * @result 'Sat'
    *
    * @param abbreviated Boolean When set to true the name will be abbreviated.
    * @name getDayName
    * @type String
    * @cat Plugins/Methods/Date
    */
   add("getDayName", function(abbreviated) {
     return abbreviated ? Date.abbrDayNames[this.getDay()] : Date.dayNames[this.getDay()];
   });

   /**
    * Gets the name of the month.
    *
    * @example var dtm = new Date("01/12/2008");
    * dtm.getMonthName();
    * @result 'Janurary'
    *
    * @example var dtm = new Date("01/12/2008");
    * dtm.getMonthName(true);
    * @result 'Jan'
    *
    * @param abbreviated Boolean When set to true the name will be abbreviated.
    * @name getDayName
    * @type String
    * @cat Plugins/Methods/Date
    */
   add("getMonthName", function(abbreviated) {
     return abbreviated ? Date.abbrMonthNames[this.getMonth()] : Date.monthNames[this.getMonth()];
   });

   /**
    * Get the number of the day of the year.
    *
    * @example var dtm = new Date("01/12/2008");
    * dtm.getDayOfYear();
    * @result 11
    *
    * @name getDayOfYear
    * @type Number
    * @cat Plugins/Methods/Date
    */
   add("getDayOfYear", function() {
     var tmpdtm = new Date("1/1/" + this.getFullYear());
     return Math.floor((this.getTime() - tmpdtm.getTime()) / 86400000);
   });

   /**
    * Get the number of the week of the year.
    *
    * @example var dtm = new Date("01/12/2008");
    * dtm.getWeekOfYear();
    * @result 2
    *
    * @name getWeekOfYear
    * @type Number
    * @cat Plugins/Methods/Date
    */
   add("getWeekOfYear", function() {
     return Math.ceil(this.getDayOfYear() / 7);
   });

   /**
    * Set the day of the year.
    *
    * @example var dtm = new Date("01/12/2008");
    * dtm.setDayOfYear(1);
    * dtm.toString();
    * @result 'Tue Jan 01 2008 00:00:00'
    *
    * @name setDayOfYear
    * @type Date
    * @cat Plugins/Methods/Date
    */
   add("setDayOfYear", function(day) {
     this.setMonth(0);
     this.setDate(day);
     return this;
   });

   /**
    * Add a number of years to the date object.
    *
    * @example var dtm = new Date("01/12/2008");
    * dtm.addYears(1);
    * dtm.toString();
    * @result 'Mon Jan 12 2009 00:00:00'
    *
    * @name addYears
    * @type Date
    * @cat Plugins/Methods/Date
    */
   add("addYears", function(num) {
     this.setFullYear(this.getFullYear() + num);
     return this;
   });

   /**
    * Add a number of months to the date object.
    *
    * @example var dtm = new Date("01/12/2008");
    * dtm.addMonths(1);
    * dtm.toString();
    * @result 'Tue Feb 12 2008 00:00:00'
    *
    * @name addMonths
    * @type Date
    * @cat Plugins/Methods/Date
    */
   add("addMonths", function(num) {
     var tmpdtm = this.getDate();

     this.setMonth(this.getMonth() + num);

     if (tmpdtm > this.getDate())
       this.addDays(-this.getDate());

     return this;
   });

   /**
    * Add a number of days to the date object.
    *
    * @example var dtm = new Date("01/12/2008");
    * dtm.addDays(1);
    * dtm.toString();
    * @result 'Sun Jan 13 2008 00:00:00'
    *
    * @name addDays
    * @type Date
    * @cat Plugins/Methods/Date
    */
   add("addDays", function(num) {
     //this.setDate(this.getDate() + num);
     this.setTime(this.getTime() + (num * 86400000));
     return this;
   });

   /**
    * Add a number of hours to the date object.
    *
    * @example var dtm = new Date("01/12/2008");
    * dtm.addHours(24);
    * dtm.toString();
    * @result 'Sun Jan 13 2008 00:00:00'
    *
    * @name addHours
    * @type Date
    * @cat Plugins/Methods/Date
    */
   add("addHours", function(num) {
     this.setHours(this.getHours() + num);
     return this;
   });

   /**
    * Add a number of minutes to the date object.
    *
    * @example var dtm = new Date("01/12/2008");
    * dtm.addMinutes(60);
    * dtm.toString();
    * @result 'Sat Jan 12 2008 01:00:00'
    *
    * @name addMinutes
    * @type Date
    * @cat Plugins/Methods/Date
    */
   add("addMinutes", function(num) {
     this.setMinutes(this.getMinutes() + num);
     return this;
   });

   /**
    * Add a number of seconds to the date object.
    *
    * @example var dtm = new Date("01/12/2008");
    * dtm.addSeconds(60);
    * dtm.toString();
    * @result 'Sat Jan 12 2008 00:01:00'
    *
    * @name addSeconds
    * @type Date
    * @cat Plugins/Methods/Date
    */
   add("addSeconds", function(num) {
     this.setSeconds(this.getSeconds() + num);
     return this;
   });

   /**
    * Sets the time component of this Date to zero for cleaner, easier comparison of dates where time is not relevant.
    *
    * @example var dtm = new Date();
    * dtm.zeroTime();
    * dtm.toString();
    * @result 'Sat Jan 12 2008 00:01:00'
    *
    * @name zeroTime
    * @type Date
    * @cat Plugins/Methods/Date
    * @author Kelvin Luck
    */
   add("zeroTime", function() {
     this.setMilliseconds(0);
     this.setSeconds(0);
     this.setMinutes(0);
     this.setHours(0);
     return this;
   });

   /**
    * Returns a string representation of the date object according to Date.format.
    * (Date.toString may be used in other places so I purposefully didn't overwrite it)
    *
    * @example var dtm = new Date("01/12/2008");
    * dtm.asString();
    * @result '12/01/2008' // (where Date.format == 'dd/mm/yyyy'
    *
    * @name asString
    * @type Date
    * @cat Plugins/Methods/Date
    * @author Kelvin Luck
    */
   add("asString", function(format) {
     var r = format || Date.format;
     if (r.split('mm').length > 1) { // ugly workaround to make sure we don't replace the m's in e.g. noveMber
       r = r.split('mmmm').join(this.getMonthName(false))
         .split('mmm').join(this.getMonthName(true))
         .split('mm').join(_zeroPad(this.getMonth() + 1));
     } else {
       r = r.split('m').join(this.getMonth() + 1);
     }
     r = r.split('yyyy').join(this.getFullYear())
       .split('yy').join((this.getFullYear() + '').substring(2))
       .split('dd').join(_zeroPad(this.getDate()))
       .split('d').join(this.getDate());
     return r;
   });

   /**
    * Returns a new date object created from the passed String according to Date.format or false if the attempt to do this results in an invalid date object
    * (We can't simple use Date.parse as it's not aware of locale and I chose not to overwrite it incase it's functionality is being relied on elsewhere)
    *
    * @example var dtm = Date.fromString("12/01/2008");
    * dtm.toString();
    * @result 'Sat Jan 12 2008 00:00:00' // (where Date.format == 'dd/mm/yyyy'
    *
    * @name fromString
    * @type Date
    * @cat Plugins/Methods/Date
    * @author Kelvin Luck
    */
   Date.fromString = function(s) {
     var f = Date.format;

     var d = new Date('01/01/1970');

     if (s == '') return d;

     s = s.toLowerCase();
     var matcher = '';
     var order = [];
     var r = /(dd?d?|mm?m?|yy?yy?)+([^(m|d|y)])?/g;
     var results;
     while ((results = r.exec(f)) != null) {
       switch (results[1]) {
         case 'd':
         case 'dd':
         case 'm':
         case 'mm':
         case 'yy':
         case 'yyyy':
           matcher += '(\\d+\\d?\\d?\\d?)+';
           order.push(results[1].substr(0, 1));
           break;
         case 'mmm':
           matcher += '([a-z]{3})';
           order.push('M');
           break;
       }
       if (results[2]) {
         matcher += results[2];
       }

     }
     var dm = new RegExp(matcher);
     var result = s.match(dm);
     for (var i = 0; i < order.length; i++) {
       var res = result[i + 1];
       switch (order[i]) {
         case 'd':
           d.setDate(res);
           break;
         case 'm':
           d.setMonth(Number(res) - 1);
           break;
         case 'M':
           for (var j = 0; j < Date.abbrMonthNames.length; j++) {
             if (Date.abbrMonthNames[j].toLowerCase() == res) break;
           }
           d.setMonth(j);
           break;
         case 'y':
           d.setYear(res);
           break;
       }
     }

     return d;
   };

   // utility method
   var _zeroPad = function(num) {
     var s = '0' + num;
     return s.substring(s.length - 2);
     //return ('0'+num).substring(-2); // doesn't work on IE :(
   };

 })();
 
 }));