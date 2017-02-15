/**
 * jQuery Transliterate plugin
 *
 * Copyright (c) 2013 Vasyliev Andrii
 * Dual licensed under the MIT (MIT-LICENSE.txt)
 * and GPL (GPL-LICENSE.txt) licenses.
 *
 * @author  Andrii Vasyliev contact@andrey-vasiliev.com
 * @link    http://andrey-vasiliev.com/
 * @version 0.0.1
 */
;
(function ($) {
    $.fn.transliterate = function (sourceText, ruleName, options) {
        if (!$.fn.transliterate.rules.hasOwnProperty(ruleName)) {
            throw new Error('Rule was not found');
        }

        options = $.fn.extend($.fn.defaults, options);
        var rules = $.fn.transliterate.rules[ruleName];

        var words = sourceText.split(/[-_ \n]/);
        for (var n in words) {
            var word = words[n];

            for (var ruleNumber in rules) {
                //console.log(rules[ruleNumber]['replace']);
                word = word.replace(
                    new RegExp(rules[ruleNumber]['pattern'], 'gm'),
                    rules[ruleNumber]['replace']
                );
                
            }
            console.log(new RegExp(rules[ruleNumber]['pattern'], 'g'),
                    rules[ruleNumber]['replace']);
            sourceText = sourceText.replace(words[n], word);
        }

        if (options['lowercase']) {
            sourceText = sourceText.toLowerCase();
        }

        return sourceText;
    };

    $.fn.transliterate.registerRules = function (rulesName, rules) {
        $.fn.transliterate.rules[rulesName] = rules;
    };

    // Default rules
    $.fn.transliterate.rules = {};
    $.fn.defaults = {
        'lowercase': true
    };
})(jQuery);