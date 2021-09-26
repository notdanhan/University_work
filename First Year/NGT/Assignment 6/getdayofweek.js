function show_hide(calgen) {
	let disp = document.getElementById("calendar");
	if (disp.style.display == "none") disp.style.display = "table";
	else if (calgen == 1) return;
	else disp.style.display = "none";
}
function is_leap(year) {
	// write your code here!
		if (year % 4 == 0) {
			if (year % 100 == 0 && year % 400 != 0 ) 
				return 0;
			else 
				return 1;
		}
		return 0;
}
function get_m(month,year) {
	const storage = [[0,3,3,6,1,4,6,2,5,0,3,5],[0,3,4,0,2,5,0,3,6,1,4,6]];
	return storage[is_leap(year)][month];
}
function r(val1,val2) {
	return val1 % val2;
}
function get_weekday(month,year) {
	return r(1 + get_m(month,year) + (5 * r((year-1),100)) + (4 * r(year - 1,100)) + (6 * r(year-1,100)),7);
}
function get_days(month,year) {
	if (month == 8 || month == 3 || month == 5 || month == 10) 
		return 30;
	else if(month != 2) 
		return 31;
	else 
		return 28 + is_leap(year);
}
function GenerateCalendar() {
	const day = get_days(month,year);
	const fday = get_weekday(month,year);
	const calendar_count = day + fday + ((day+fday)%7 );
	const date = new Date();
	const day_today = date.getUTCDate();
	let output_string = "";
	for (let i = 0; i < calendar_count;i++){
		if(i % 7 == 0) output_string+="<tr>";
		if(i < fday || (i - fday) >= day) output_string+="<td></td>";
		else {
			if (1 + i - fday == day_today) output_string += "<td class=\"today\">" + (1+i-fday) + "</td>";
			else output_string+= "<td>" + (1 + i - fday) + "</td>";
		}
		if(i%7 == 6) {
			output_string+= "</tr>";
			if ((i - fday) >= (day - 1)) break;
		}
	}
	document.getElementById("calendar-days").innerHTML = output_string;
	show_hide(1);
}