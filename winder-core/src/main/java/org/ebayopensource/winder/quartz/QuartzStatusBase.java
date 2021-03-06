/**
 * Copyright (c) 2016 eBay Software Foundation. All rights reserved.
 *
 * Licensed under the MIT license.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 *
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.ebayopensource.winder.quartz;

import org.ebayopensource.common.util.Parameters;
import org.ebayopensource.winder.WinderEngine;

import java.util.Date;

/**
 * Base Status
 *
 * @author Sheldon Shao xshao@ebay.com on 10/16/16.
 * @version 1.0
 */
public abstract class QuartzStatusBase<S extends Enum> {

    protected transient Date dateCreated;

    protected WinderEngine engine;

    protected Parameters<Object> parameters;

    protected int maxMessages;

    public QuartzStatusBase(WinderEngine engine, Parameters<Object> parameters) {
        this.engine = engine;
        this.parameters = parameters;
        getDateCreated();
        this.maxMessages = engine.getConfiguration().getInt("winder.task.maxMessage", 1000);
    }

    public Date getDateCreated() {
        if (dateCreated == null) {
            dateCreated = parameters.getDate(QuartzWinderConstants.KEY_DATE_CREATED);
            if (dateCreated == null) {
                dateCreated = new Date();
                parameters.put(QuartzWinderConstants.KEY_DATE_CREATED, dateCreated.getTime());
            }
        }
        return dateCreated;
    }

    public String getMessage() {
        return parameters.getString(QuartzWinderConstants.KEY_MESSAGE);
    }

    public void setMessage(String message) {
        parameters.put(QuartzWinderConstants.KEY_MESSAGE, message);
    }

    public Parameters<Object> toParameters() {
        return parameters;
    }
}
