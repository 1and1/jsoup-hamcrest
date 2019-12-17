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


import static net.oneandone.jsoup.hamcrest.fluent.DocumentAssertions.anElement;
import static net.oneandone.jsoup.hamcrest.fluent.DocumentAssertions.eachElement;
import static net.oneandone.jsoup.hamcrest.fluent.DocumentAssertions.elements;
import static net.oneandone.jsoup.hamcrest.fluent.JsoupAssertions.html;
import static org.hamcrest.CoreMatchers.hasItem;

import org.junit.Test;

public class AllMatcherFailTest extends HtmlBaseTest {

    @Test(expected = AssertionError.class)
    public void exists() {
        html(source)
                .expect(anElement("foo").exists());
    }

    @Test(expected = AssertionError.class)
    public void tooMany() {
        html(source)
                .expect(anElement("input").exists());
    }

    @Test(expected = AssertionError.class)
    public void hasValue() {
        html(source)
                .expect(anElement("form input[name=_csrf]")
                        .hasValue("5ee91155-9809-4630"));
    }

    @Test(expected = AssertionError.class)
    public void hasAttributeExits() {
        html(source)
                .expect(anElement("#exampleInputEmail")
                        .hasAttribute("readonly"));
    }

    @Test(expected = AssertionError.class)
    public void hasAttribute() {
        html(source)
                .expect(anElement("#exampleInputEmail")
                        .hasAttribute("placeholder", "Foo"));
    }

    @Test(expected = AssertionError.class)
    public void hasCssClass() {
        html(source)
                .expect(anElement("button[type=submit]")
                        .hasCssClass("btn-primary"));
    }

    @Test(expected = AssertionError.class)
    public void hasCss() {
        html(source)
                .expect(anElement("button[type=submit]")
                        .hasCss(hasItem("btn-primary")));
    }

    @Test(expected = AssertionError.class)
    public void hasText() {
        html(source)
                .expect(anElement(".content > h1")
                        .hasText("This is a production site"));
    }

    @Test(expected = AssertionError.class)
    public void hasOwnText() {
        html(source)
                .expect(anElement(".content > h1")
                        .hasOwnText("This is not a site"));
    }

    @Test(expected = AssertionError.class)
    public void hasData() {
        html(source)
                .expect(anElement("script[language=JavaScript]")
                        .hasData("5ee91155-9809-4630-81a5-47d478eccd11"));
    }

    @Test(expected = AssertionError.class)
    public void eachElementFail() {
        html(source)
                .expect(eachElement("label")
                        .hasOwnText("Name"));
    }

    @Test(expected = AssertionError.class)
    public void elementsFail() {
        html(source)
                .expect(elements("script[language=JavaScript]").hasCount(2));
    }

    @Test(expected = AssertionError.class)
    public void hasTitleFail() {
        html(source).expect(DocumentMatchers.hasTitle("Fail"));
    }

    @Test(expected = AssertionError.class)
    public void elementsEachFail() {
        html(source)
                .expect(elements("script[language=JavaScript]")
                        .each().hasData("5ee91155-9809-4630-81a5-47d478eccd11"));
    }
}
