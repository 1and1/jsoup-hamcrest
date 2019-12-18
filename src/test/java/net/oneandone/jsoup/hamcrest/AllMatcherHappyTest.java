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


import static net.oneandone.jsoup.hamcrest.ElementMatchers.predicate;
import static net.oneandone.jsoup.hamcrest.fluent.DocumentAssertions.anElement;
import static net.oneandone.jsoup.hamcrest.fluent.DocumentAssertions.elements;
import static net.oneandone.jsoup.hamcrest.fluent.JsoupAssertions.html;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;

public class AllMatcherHappyTest extends HtmlBaseTest {


    @Test
    public void hasValue() {
        html(source)
                .expect(anElement("form input[name=_csrf]")
                        .hasValue("5ee91155-9809-4630-81a5-47d478eccd11")
                        .hasValue(equalTo("5ee91155-9809-4630-81a5-47d478eccd11")));
    }

    @Test
    public void hasAttribute() {
        html(source)
                .expect(anElement("#exampleInputEmail")
                        .hasAttribute("placeholder")
                        .hasAttribute("placeholder", "Email")
                        .hasAttribute("placeholder", equalTo("Email")));
    }

    @Test
    public void hasCssClass() {
        html(source)
                .expect(anElement("button[type=submit]")
                        .hasCssClass("btn-default")
                        .hasCss(hasItem("btn")));
    }

    @Test
    public void hasText() {
        html(source)
                .expect(anElement(".content > h1")
                        .hasText("This is a demo site")
                        .hasText(containsString("demo")));
    }

    @Test
    public void hasOwnText() {
        html(source)
                .expect(anElement(".content > h1")
                        .hasOwnText("This is a site")
                        .hasOwnText(equalTo("This is a site")));
    }

    @Test
    public void hasData() {
        html(source)
                .expect(anElement("script[language=JavaScript]")
                        .hasData("\n        var xsrf_token = \"5ee91155-9809-4630-81a5-47d478eccd11\";\n    ")
                        .hasData(containsString("5ee91155-9809-4630-81a5-47d478eccd11")));
    }

    @Test
    public void custom() {
        html(source)
                .expect(anElement(".content > h1")
                        .matches(predicate(element -> element.child(0).tagName().equals("small"),
                                description -> {
                                }, (element, description) -> {
                                })));
    }


    @Test
    public void elementsHasCount() {
        html(source)
                .expect(elements("form input[type=submit], form button[type=submit]")
                        .hasCount(1)
                        .hasCount(equalTo(1)));
    }

    @Test
    public void hasTitleMatcher() {
        html(source).expect(DocumentMatchers.hasTitle("Demo"));
    }

    @Test
    public void existingDocument() {
        Document document = Jsoup.parse(source);
        html(document).expect(DocumentMatchers.hasTitle("Demo"));
    }
}
