# ComplexAlarm

Alarm Clock application for Android taken to the extrem based on the [opening_hours syntax][oh:specification]. Set your alarms *exactly* the way you want to.

## State of the application

This application is currently in a pre-alpha state meaning that the basic features are not yet implemented nor tested.

## Why do I want to use this?

You might consider using this application if you have tried out all the Alarm applications out there but you always missed certain features to customize your alarms.

I can pretty much promise you (although I have only tested a few Alarm applications) that this application is the most powerful alarm application out there. Unfortunately, you do not get all this power for free. You will have to express your alarms in a certain syntax as described below.

## Example alarm times

For example you can define your alarms as follows:

* Monday till Wednesday: 07:00
* Thursday and Friday: 05:45
* But the first Monday of the month: 07:30
* If there are currently school holidays Monday-Friday: Three hours after sunrise
* No alarms on public holidays

This can be expressed in the [opening_hours syntax][oh:specification] as [`Mo-We 07:00; Th,Fr 05:45; Mo[1] 07:30; SH Mo-Fr (sunrise+03:00); PH off`](http://openingh.openstreetmap.de/evaluation_tool/?EXP=Mo-We%2007%3A00%3B%20Th%2CFr%2005%3A45%3B%20Mo[1]%2007%3A30%3B%20SH%20Mo-Fr%20%28sunrise%2B03%3A00%29%3B%20PH%20off&DATE=1401314460000&lat=48.7769&lon=9.1844&mode=2).

## Features

Supports all the features of the [opening_hours syntax][oh:specification] which include:

* [Public/School holiday][oh:specification:holiday_type]
* [Variable dates like easter][oh:specification:holiday_type]
* [Variable times like sunrise and sunset][oh:specification:variable_date]

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

* Used [Alarm Klock](https://code.google.com/p/kraigsandroid/) as reference implementation, which is licensed under Apache License 2.0.
* The [application icon](ic_launcher-web.png) is based on [Zahnrad. / Gear.](https://secure.flickr.com/photos/stefan-w/3337070623) by [Stefan W.](https://secure.flickr[com/photos/stefan-w/). I cropped the picture and added the letters ["C", "A"] which stands for ComplexAlarm. Both the original and this modification are under [Creative Commons Attribution 2.0 Generic (CC BY 2.0)](https://creativecommons.org/licenses/by/2.0/).

## Author

* [Robin Schneider](https://github.com/ypid)

## License

This work is released under GPLv3.


[OSM-wiki]: https://wiki.openstreetmap.org
[oh:specification]: https://wiki.openstreetmap.org/wiki/Key:opening_hours:specification
[oh:specification:holiday_type]: https://wiki.openstreetmap.org/wiki/Key:opening_hours:specification#holiday_type
[oh:specification:variable_date]: https://wiki.openstreetmap.org/wiki/Key:opening_hours:specification#variable_date
