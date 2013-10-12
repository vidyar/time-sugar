# TimeSugar

Syntax sugar for java.time (JSR-310; Three-Ten).

[![Build Status](https://travis-ci.org/watermint/time-sugar.png)](https://travis-ci.org/watermint/time-sugar)

# Synopsis

SugarParser returns java.time.Instant as Optional.

	SugarParser sp = new SugarParser("yyyy/MM/dd");
    
    // Parse string with default time zone
	Optional<Instant> d1 = sp.parse("2013-10-13");
	Optional<Instant> d2 = sp.parse("2013-10-13", ZoneId.of("Asia/Tokyo"));
	Optional<Instant> d3 = sp.parse("2013-10-13", ZoneOffset.of("+09:00"));
    
	// get instant from Optional
	System.out.println(d2.get); // 2013-10-12T15:00:00Z

SugarParser supports multiple possible date/time format.

	SugarParser sp = new SugarParser("yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss");
    
    Optional<Instant> d4 = sp.parse("2013-10-13");
    Optional<Instant> d5 = sp.parse("2013-10-13 08:00:00");

Static functions also available:

    SugarParser.parseWithPatterns("2013-10-13", "yyyy-MM-dd");
    SugarParser.parseWithPatterns("2013-10-13", "yyyy-MM-dd", "yyyy/MM/dd");
    SugarParser.parseWithPatterns("2013-10-13", ZoneId.of("Asia/Tokyo"), "yyyy-MM-dd");

# Requirement

Java SE 8 or later.

# License

The MIT License (MIT) Copyright (c) 2013 Takayuki Okazaki

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
