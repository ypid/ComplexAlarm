# Application and UI design

## Integration with opening_hours and abstraction

The main activity consists of a list of rules. One rule per line. To evaluate the next alarm, the rules are combined using [a rule separator][oh:spec:any_rule_separator]. The resulting string is fed into opening_hours.js. The position of the rule counts so it should be easy to change the order of the rules.

[oh:spec:any_rule_separator]: https://wiki.openstreetmap.org/wiki/Key:opening_hours:specification#any_rule_separator
