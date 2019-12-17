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

import java.util.stream.Collectors;

import org.hamcrest.CoreMatchers;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class SingleElementAssertions extends AbstractElementAssertions<SingleElementAssertions> {

    SingleElementAssertions(String css) {
        super(css);
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
        assertThat(select.first(), new AllOfMatcher<>(description ->
                description
                        .appendText("a single element selected by ")
                        .appendValue(getCss())
                        .appendText(" matching "),
                getMatchers()));
    }

    /**
     * Asserts that the selected element actually is present.
     *
     * @return this for fluent chaining
     */
    public SingleElementAssertions exists() {
        getMatchers().add(CoreMatchers.notNullValue(Element.class));
        return this;
    }
}
