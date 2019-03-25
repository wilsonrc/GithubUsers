/*******************************************************************************
 *  Copyright (c) 2011 GitHub Inc.
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License 2.0
 *  which accompanies this distribution, and is available at
 *  https://www.eclipse.org/legal/epl-2.0/
 *
 *  SPDX-License-Identifier: EPL-2.0
 *
 *  Contributors:
 *    Kevin Sawicki (GitHub Inc.) - initial API and implementation
 *******************************************************************************/
package com.wilsonrc.githubusers.data.source.remote

import com.wilsonrc.githubusers.data.models.User
import retrofit2.Response


class PageLinks(response: Response<List<User>>) {

    private val HEADER_LINK = "Link"
    private val HEADER_NEXT = "X-Next"
    private val HEADER_LAST = "X-Last"
    private val META_REL = "rel"
    private val META_LAST = "last"
    private val META_NEXT = "next"
    private val META_FIRST = "first"
    private val META_PREV = "prev"
    private val DELIM_LINKS = ","
    private val DELIM_LINK_PARAM = ";"

    var first: String? = null
    var last: String? = null
    var next: String? = null
    var prev: String? = null

    init {
        val linkHeader = response.headers().get(HEADER_LINK)
        if (linkHeader != null) {
            val links = linkHeader!!.split(DELIM_LINKS.toRegex()).dropLastWhile({ it.isEmpty() })
                .toTypedArray()
            for (link in links) {
                val segments =
                    link.split(DELIM_LINK_PARAM.toRegex()).dropLastWhile({ it.isEmpty() })
                        .toTypedArray()
                if (segments.size < 2)
                    continue

                var linkPart = segments[0].trim({ it <= ' ' })
                if (!linkPart.startsWith("<") || !linkPart.endsWith(">"))
                //$NON-NLS-1$ //$NON-NLS-2$
                    continue
                linkPart = linkPart.substring(1, linkPart.length - 1)

                for (i in 1 until segments.size) {
                    val rel = segments[i].trim({ it <= ' ' }).split("=".toRegex())
                        .dropLastWhile { it.isEmpty() }.toTypedArray() //$NON-NLS-1$
                    if (rel.size < 2 || !META_REL.equals(rel[0]))
                        continue

                    var relValue = rel[1]
                    if (relValue.startsWith("\"") && relValue.endsWith("\""))
                    //$NON-NLS-1$ //$NON-NLS-2$
                        relValue = relValue.substring(1, relValue.length - 1)

                    if (META_FIRST == relValue)
                        first = linkPart
                    else if (META_LAST == relValue)
                        last = linkPart
                    else if (META_NEXT == relValue)
                        next = linkPart
                    else if (META_PREV == relValue)
                        prev = linkPart
                }
            }
        } else {
            next = response.headers().get(HEADER_NEXT)
            last = response.headers().get(HEADER_LAST)
        }
    }
}