# ComplexAlarm

Alarm clock application for Android taken to the extrem based on the [opening_hours syntax][oh:specification]. Set your alarms *exactly* the way you want to.

## State of the application

This application is currently in a pre-alpha state meaning that the basic features are not yet implemented nor tested.

## Why do I want to use this?

You might consider using this application if you have tried out all the alarm clock applications out there but you always missed certain features to customize your alarms.

I can pretty much promise you (although I have only tested a few alarm clock applications) that this application is the most powerful alarm clock application out there. Unfortunately, you do not get all this power for free. You will have to express your alarms in a certain syntax as described below.

## Example alarm times

For example you can define your alarms as follows:

* Monday till Wednesday: 07:00
* Thursday and Friday: 05:45
* But the first Monday of the month: 07:30
* If there are currently school holidays Monday-Friday: Three hours after sunrise
* No alarms on public holidays

This can be expressed in the [opening_hours syntax][oh:specification] as [`Mo-We 07:00; Th,Fr 05:45; Mo[1] 07:30; SH Mo-Fr (sunrise+03:00); PH off`](http://openingh.openstreetmap.de/evaluation_tool/?EXP=Mo-We%2007%3A00%3B%20Th%2CFr%2005%3A45%3B%20Mo[1]%2007%3A30%3B%20SH%20Mo-Fr%20%28sunrise%2B03%3A00%29%3B%20PH%20off&DATE=1401314460000&lat=48.7769&lon=9.1844&mode=1).

---------------------------------------

But we are just getting started ;) Consider the following, slightly advancer definition:

* Monday till Wednesday: 07:00
* Thursday: 05:45
* Friday:
  * For even weeks: 05:45
  * For odd weeks: 07:05
* If there are currently school holidays Monday-Friday: Three hours after sunrise
* No alarms on public holidays

This can be expressed as [`Mo-We 07:00; Th 05:45; week 1-53/2 Fr 07:05; week 2-53/2 Fr 05:45; SH Mo-Fr (sunrise+03:00); PH off`](http://openingh.openstreetmap.de/evaluation_tool/?EXP=Mo-We%2007%3A00%3B%20Th%2005%3A45%3B%20week%201-53%2F2%20Fr%2007%3A05%3B%20week%202-53%2F2%20Fr%2005%3A45%3B%20SH%20Mo-Fr%20%28sunrise%2B03%3A00%29%3B%20PH%20off&DATE=1411390920000&lat=48.7769&lon=9.1844&mode=1).

---------------------------------------

If you really know the syntax you can even do stuff like this example. Assume you enjoy jogging in the morning and you really like to see the sunrise when you are jogging around the sea but on the other hand you don‘t want to get up too early. So you have agreed on the following trade-off. You get out to see the sunrise if that does not result in getting woken up before 07:00 (remember, it is Saturday ;) ). If you had to stand up before 07:00 to still see the sunrise you rather lay in bed until 07:00. This is what the following value does express …

* Wake me on Saturday one hour before sunrise if that is after 07:00 else wake me 07:00.

[`Sa 07:00; (sunrise-05:00)-(sunrise-01:00) off, Sa (sunrise-01:00); 00:00-07:00 off`](http://openingh.openstreetmap.de/evaluation_tool/?EXP=Sa%2007%3A00%3B%20%28sunrise-05%3A00%29-%28sunrise-01%3A00%29%20off%2C%20Sa%20%28sunrise-01%3A00%29%3B%2000%3A00-07%3A00%20off&DATE=1418451240000&lat=48.8769&lon=7.1844&mode=2)

<!-- Sa 07:00 open "sunrise would be too early …"; (sunrise-05:00)-(sunrise-01:00) off, Sa (sunrise-01:00) open "get up, the sun is about to show up"; 00:00-07:00 off -->

---------------------------------------

And last but not least, you could additionally enter the dates where an alarm is not wanted:

* Do not alarm in the four days around easter.
* Do not alarm in my vacations. Example 2014 Sep 1-2014 Sep 7

This can be expressed as [`Mo-We 07:00; Th 05:45; week 1-53/2 Fr 07:05; week 2-53/2 Fr 05:45; SH Mo-Fr (sunrise+03:00); PH off; easter -2 days-easter +2 days off "My little break from work every year."; 2014 Sep 1-2014 Sep 7 off "My vacations …"`](http://openingh.openstreetmap.de/evaluation_tool/?EXP=Mo-We%2007%3A00%3B%20Th%2005%3A45%3B%20week%201-53%2F2%20Fr%2007%3A05%3B%20week%202-53%2F2%20Fr%2005%3A45%3B%20SH%20Mo-Fr%20%28sunrise%2B03%3A00%29%3B%20PH%20off%3B%20easter%20-2%20days-easter%20%2B2%20days%20off%20%22My%20little%20break%20from%20work%20every%20year.%22%3B%202014%20Sep%201-2014%20Sep%207%20off%20%22My%20vacations%20%E2%80%A6%22&DATE=1459198860000&lat=48.7769&lon=9.1844&mode=1).

## Features

Supports all the features of the [opening_hours syntax][oh:specification] which include:

* [Public/School holiday][oh:specification:holiday_type]
* [Variable dates like easter][oh:specification:variable_date]
* [Variable times like sunrise and sunset][oh:specification:event]

## Why was this application written?

There are a few reasons why I wrote this thing:

* Just to try something. The [opening_hours syntax][oh:specification] has become so powerful over time and the days where the syntax was only used to describe opening hours of facilities in [OpenStreetMap][OSM-wiki] are long over. So I thought why not reuse this for something completely different :smile:
* To get experience with Android application development and design (this is my first app).
* Help to get the full syntax support in [OsmAnd](http://osmand.net/) (and maybe others) some day.
* Maybe write a simple opening_hours editor for mappers (OpenStreetMap contributors) on Android.
* Some people might even use this if they really want to go crazy with there alarm times.

## Dependencies

* [opening_hours.js](https://github.com/ypid/opening_hours.js): JavaScript library to evaluate opening_hours.
* [js-evaluator-for-android](https://github.com/evgenyneu/js-evaluator-for-android): Evaluate JavaScript on Android.

## Credits

* Used [Alarm Klock](https://code.google.com/p/kraigsandroid/) as reference implementation and reused most of it, which is licensed under Apache License 2.0. Any improvements will be contributed back under Apache License 2.0.
* The [application icon](ic_launcher-web.png) is based on [Zahnrad. / Gear.](https://secure.flickr.com/photos/stefan-w/3337070623) by [Stefan W.](https://secure.flickr[com/photos/stefan-w/). I cropped the picture and added the letters ["C", "A"] which stand for ComplexAlarm. Both the original and this modification are under [Creative Commons Attribution 2.0 Generic (CC BY 2.0)](https://creativecommons.org/licenses/by/2.0/).

## Author

* [Robin Schneider](https://github.com/ypid)

## License

This work is released under GPLv3.


[OSM-wiki]: https://wiki.openstreetmap.org
[oh:specification]: https://wiki.openstreetmap.org/wiki/Key:opening_hours:specification
[oh:specification:holiday_type]: https://wiki.openstreetmap.org/wiki/Key:opening_hours:specification#holiday_type
[oh:specification:variable_date]: https://wiki.openstreetmap.org/wiki/Key:opening_hours:specification#variable_date
[oh:specification:event]: https://wiki.openstreetmap.org/wiki/Key:opening_hours:specification#event
