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

import static org.hamcrest.MatcherAssert.assertThat;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import net.oneandone.jsoup.hamcrest.DocumentMatcher;

/**
 * Applies the given matcher against each element separately.
 *
 * See {@link DocumentAssertions#eachElement(String)}.
 */
public class EachElementAssertions extends AbstractElementAssertions<EachElementAssertions> {

    private final DocumentMatcher parent;

    EachElementAssertions(String css) {
        super(css);
        parent = null;
    }

    EachElementAssertions(String css, DocumentMatcher parent) {
        super(css);
        this.parent = parent;
    }

    @Override
    public void match(Document document) {
        if (parent != null) {
            parent.match(document);
        }
        Elements select = document.select(getCss());
        assertThat(select, new EachElementMatcher(new AllOfMatcher<>(description ->
                description.appendText("elements selected by ").appendValue(getCss())
                        .appendText(" matching "), getMatchers())));
    }
}
