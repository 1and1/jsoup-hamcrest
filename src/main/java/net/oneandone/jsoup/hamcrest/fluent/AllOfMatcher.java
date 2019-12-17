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

import java.util.function.Consumer;

import org.hamcrest.Description;
import org.hamcrest.DiagnosingMatcher;
import org.hamcrest.Matcher;

/**
 * Internal API
 */
class AllOfMatcher<T> extends DiagnosingMatcher<T> {

    private Consumer<Description> description;

    private final Iterable<Matcher<? super T>> matchers;

    AllOfMatcher(Consumer<Description> description, Iterable<Matcher<? super T>> matchers) {
        this.description = description;
        this.matchers = matchers;
    }

    @Override
    protected boolean matches(Object item, Description mismatchDescription) {
        boolean result = true;
        for (Matcher<? super T> matcher : matchers) {
            if (!matcher.matches(item)) {
                mismatchDescription.appendDescriptionOf(matcher).appendText(" ");
                matcher.describeMismatch(item, mismatchDescription);
                result = false;
            }
        }
        return result;
    }

    @Override
    public void describeTo(Description description) {
        this.description.accept(description);
        description.appendList("(", " " + "and" + " ", ")", matchers);
    }
}
