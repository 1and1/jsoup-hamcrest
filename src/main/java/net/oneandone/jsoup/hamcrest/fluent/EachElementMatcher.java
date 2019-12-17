/*
 * Copyright 1&1 Internet AG, https://github.com/1and1/
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.oneandone.jsoup.hamcrest.fluent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Internal API
 */
class EachElementMatcher extends TypeSafeDiagnosingMatcher<Elements> {

    private final Matcher<? super Element> matcher;

    EachElementMatcher(Matcher<? super Element> matcher) {
        this.matcher = matcher;
    }

    @Override
    protected boolean matchesSafely(Elements item, Description mismatchDescription) {
        boolean result = true;
        for (Element element : item) {
            if (!matcher.matches(element)) {
                if (!result) {
                    mismatchDescription.appendText("\n");
                }
                mismatchDescription.appendText("element ").appendValue(element.cssSelector()).appendText(" did not match ");
                matcher.describeMismatch(element, mismatchDescription);
                result = false;
            }
        }
        return result;
    }

    @Override
    public void describeTo(Description description) {
        description.appendDescriptionOf(matcher);
    }
}
