/**
 *    Copyright 2020-present  Shanghai Jinmu Information Technology Co., Ltd.
 *
 *    This program is free software: you can redistribute it and/or modify
 *    it under the terms of the Server Side Public License, version 1,
 *    as published by Shanghai Jinmu Information Technology Co., Ltd.(The name of the development team is Whaleal.)
 *
 *
 *    This program is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    Server Side Public License for more details.
 *
 *    You should have received a copy of the Server Side Public License
 *    along with this program. If not, see
 *    <http://www.whaleal.com/licensing/server-side-public-license>.
 *
 *    As a special exception, the copyright holders give permission to link the
 *    code of portions of this program with the OpenSSL library under certain
 *    conditions as described in each individual source file and distribute
 *    linked combinations including the program with the OpenSSL library. You
 *    must comply with the Server Side Public License in all respects for
 *    all of the code used other than as permitted herein. If you modify file(s)
 *    with this exception, you may extend this exception to your version of the
 *    file(s), but you are not obligated to do so. If you do not wish to do so,
 *    delete this exception statement from your version. If you delete this
 *    exception statement from all source files in the program, then also delete
 *    it in the license file.
 */
package com.whaleal.mars.util;

import com.mongodb.lang.Nullable;

import java.util.Comparator;
import java.util.Map;

/**
 * Contract for matching routes to patterns.
 *
 * <p>Equivalent to {@link PathMatcher}, but enables use of parsed representations
 * of routes and patterns for efficiency reasons in scenarios where routes from
 * incoming messages are continuously matched against a large number of message
 * handler patterns.
 */
public interface RouteMatcher {

    /**
     * Return a parsed representation of the given route.
     *
     * @param routeValue the route to parse
     * @return the parsed representation of the route
     */
    Route parseRoute(String routeValue);

    /**
     * Whether the given {@code route} contains pattern syntax which requires
     * the {@link #match(String, Route)} method, or if it is a regular String
     * that could be compared directly to others.
     *
     * @param route the route to check
     * @return {@code true} if the given {@code route} represents a pattern
     */
    boolean isPattern(String route);

    /**
     * Combines two patterns into a single pattern.
     *
     * @param pattern1 the first pattern
     * @param pattern2 the second pattern
     * @return the combination of the two patterns
     * @throws IllegalArgumentException when the two patterns cannot be combined
     */
    String combine(String pattern1, String pattern2);

    /**
     * Match the given route against the given pattern.
     *
     * @param pattern the pattern to try to match
     * @param route   the route to test against
     * @return {@code true} if there is a match, {@code false} otherwise
     */
    boolean match(String pattern, Route route);

    /**
     * Match the pattern to the route and extract template variables.
     *
     * @param pattern the pattern, possibly containing templates variables
     * @param route   the route to extract template variables from
     * @return a map with template variables and values
     */
    @Nullable
    Map<String, String> matchAndExtract(String pattern, Route route);

    /**
     * Given a route, return a {@link Comparator} suitable for sorting patterns
     * in order of explicitness for that route, so that more specific patterns
     * come before more generic ones.
     *
     * @param route the full path to use for comparison
     * @return a comparator capable of sorting patterns in order of explicitness
     */
    Comparator<String> getPatternComparator(Route route);


    /**
     * A parsed representation of a route.
     */
    interface Route {

        /**
         * The original route value.
         */
        String value();
    }

}
