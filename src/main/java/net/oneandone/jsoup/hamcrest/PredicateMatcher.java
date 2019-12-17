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

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Predicate;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeDiagnosingMatcher;

/**
 * Internal API
 */
class PredicateMatcher<T> extends TypeSafeDiagnosingMatcher<T> {
    private final Predicate<T> predicate;

    private final Consumer<Description> description;

    private final BiConsumer<T, Description> mismatchDescription;

    PredicateMatcher(Predicate<T> predicate, Consumer<Description> description, BiConsumer<T, Description>
            mismatchDescription) {
        this.predicate = predicate;
        this.description = description;
        this.mismatchDescription = mismatchDescription;
    }


    @Override
    public void describeTo(Description description) {
        this.description.accept(description);
    }

    @Override
    protected boolean matchesSafely(T item, Description mismatchDescription) {
        boolean match = predicate.test(item);
        if (!match) {
            this.mismatchDescription.accept(item, mismatchDescription);
        }
        return match;
    }
}
