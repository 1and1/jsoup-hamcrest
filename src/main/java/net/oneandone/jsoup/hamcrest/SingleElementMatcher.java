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

import static org.hamcrest.MatcherAssert.assertThat;

import java.util.stream.Collectors;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Internal API
 */
class SingleElementMatcher extends ElementMatcher {
    SingleElementMatcher(String css, Matcher<? super Element> matcher) {
        super(css, matcher);
    }

    @Override
    public void match(Document document) {
        Elements select = document.select(getCss());
        if (select.size() > 1) {
            throw new AssertionError(
                    String.format("Single or null Element expected for \"%s\", but found <%d>:%n%s",
                            getCss(),
                            select.size(),
                            select.stream().map(Element::cssSelector).collect(Collectors.joining("\n"))));
        }
        assertThat(select.first(), this);
    }

    @Override
    protected boolean matches(Object item, Description mismatchDescription) {
        boolean matches = getMatcher().matches(item);
        if (!matches) {
            getMatcher().describeMismatch(item, mismatchDescription);
        }
        return matches;
    }

    @Override
    public void describeTo(Description description) {
        description
                .appendText("a single element selected by ")
                .appendValue(getCss())
                .appendText(" matching ")
                .appendDescriptionOf(getMatcher());
    }
}
