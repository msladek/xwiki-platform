/**
 * ===================================================================
 *
 * Copyright (c) 2003 Ludovic Dubost, All rights reserved.
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details, published at
 * http://www.gnu.org/copyleft/lesser.html or in lesser.txt in the
 * root folder of this distribution.
 *
 * Created by
 * User: Ludovic Dubost
 * Date: 28 nov. 2003
 * Time: 11:42:38
 */
package com.xpn.xwiki.render;

import com.xpn.xwiki.util.Util;
import org.apache.oro.text.regex.*;

public class WikiSubstitution extends Perl5Substitution {

    private Pattern pattern;
    private Util util;

    public WikiSubstitution(com.xpn.xwiki.util.Util util) {
        setUtil(util);
        setSubstitution("$&");
    }

    public WikiSubstitution(Util util, String patternparam) {
        setUtil(util);
        setPattern(makePattern(patternparam));
        setSubstitution("$&");
    }

    public WikiSubstitution(Util util, String[] patternparam) {
       this.setPattern(util.getPatterns().getPattern(makePattern(patternparam)));
       setSubstitution("$&");
    }

    public void setPattern(String patternparam) {
        setPattern(util.getPatterns().getPattern(makePattern(patternparam)));
    }

    public void setPattern(String patternparam, int options) {
        setPattern(util.getPatterns().getPattern(makePattern(patternparam), options));
    }

    public String makePattern(String patternparam) {
        return patternparam;
    }


    public String makePattern(String[] patternparam) {
        return patternparam.toString();
    }

    public String substitute(String line) {
        return org.apache.oro.text.regex.Util.substitute(getMatcher(), getPattern(), this, line, org.apache.oro.text.regex.Util.SUBSTITUTE_ALL);
    }

    public Perl5Matcher getMatcher() {
        return util.getMatcher();
    }

    public Pattern getPattern() {
        return pattern;
    }

    public void setPattern(Pattern pattern) {
        this.pattern = pattern;
    }

    public void appendSubstitution(StringBuffer stringBuffer, MatchResult matchResult, int i,  PatternMatcherInput minput,
                                   PatternMatcher patternMatcher, Pattern pattern) {
      prepareSubstitution(matchResult);
      super.appendSubstitution(stringBuffer,matchResult, i, minput, patternMatcher, pattern);
    }

    public void prepareSubstitution(MatchResult matchResult) {
    }

    public com.xpn.xwiki.util.Util getUtil() {
        return util;
    }

    public void setUtil(com.xpn.xwiki.util.Util util) {
        this.util = util;
    }
}
