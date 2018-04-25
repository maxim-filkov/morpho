# Java wrapper for morphological analyzer Yandex.MyStem and PhpMorphy

## Introduction

Details about the algorithm used in Yandex.MyStem can be found in [I. Segalovich «A fast morphological algorithm with unknown word guessing induced by a dictionary for a web search engine», MLMTA-2003, Las Vegas, Nevada, USA.](http://download.yandex.ru/company/iseg-las-vegas.pdf).

The library PhpMorphy ([source](https://sourceforge.net/projects/phpmorphy/)) supports English, Russian and German languages.

The wrapper's code in under Apache 2.0 license, but please remember that Yandex.MyStem is not open source and licensed under [conditions of the Yandex License](https://legal.yandex.ru/mystem/).

This service wraps both Yandex.MyStem and PhpMorphy to increase detection probability.
The service returns results in the format word class to probability:

```
{
   "NOUN": 2
   "VERB": 1
}
```

## System Requirements

The wrapper should work on Linux and Mac OS systems.

## Examples

The APIs are available at http://localhost:8080/swagger-ui.html

## Contacts

Maxim Filkov <maximfilkov@gmail.com>

## Also please see

* https://tech.yandex.ru/mystem/
* https://nlpub.ru/Mystem
* http://phpmorphy.sourceforge.net/dokuwiki/
