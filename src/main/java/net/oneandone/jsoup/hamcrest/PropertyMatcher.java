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

package net.oneandone.jsoup.hamcrest;

import java.util.function.Consumer;
import java.util.function.Function;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;

/**
 * Internal API
 */
class PropertyMatcher<V, T> extends TypeSafeDiagnosingMatcher<V> {
    private final Function<V, T> extractor;

    protected final Matcher<T> matcher;

    private final Consumer<Description> description;

    PropertyMatcher(Consumer<Description> description, Function<V, T> extractor, Matcher<T> matcher) {
        this.description = description;
        this.extractor = extractor;
        this.matcher = matcher;
    }

    @Override
    protected boolean matchesSafely(V item, Description mismatchDescription) {
        T subject = extractor.apply(item);
        boolean matches = matcher.matches(subject);
        if (!matches) {
            matcher.describeMismatch(subject, mismatchDescription);
        }
        return matches;
    }

    @Override
    public void describeTo(Description description) {
        this.description.accept(description);
    }
}
