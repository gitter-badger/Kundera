/**
 * Copyright 2012 Impetus Infotech.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.impetus.kundera.ycsb;

import java.io.IOException;

import org.apache.commons.configuration.ConfigurationException;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.impetus.kundera.ycsb.runner.OracleNosqlRunner;

/**
 * Cassandra Kundera YCSB benchmarking.
 * 
 * @author vivek.mishra
 * 
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OracleNosqlYCSBTest extends YCSBBaseTest
{

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception
    {
        System.setProperty("fileName", "src/main/resources/db-oracle-nosql.properties");
        // in case property file name is not set as system property.
        super.setUp();
    }

    @Test
    public void onTest() throws Exception
    {
      //  testConcurrentWorkload();
        testRead();
        // testUpdate();

    }

    void testConcurrentWorkload() throws IOException, ConfigurationException
    {
        onChangeRunType("load");
        Runtime runtime = Runtime.getRuntime();
        runner.startServer(true, runtime);

        process();
    }

    void testRead() throws Exception
    {
        onChangeRunType("t");
        onRead();
    }

    void testUpdate() throws Exception
    {
        onChangeRunType(true);
        onUpdate();
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception
    {
        onDestroy();
    }

    /**
     * @param runType
     * @throws ConfigurationException
     */
    protected void onChangeRunType(final String runType) throws ConfigurationException
    {
        config.setProperty("run.type", runType);
        config.setProperty("ycsbjar.location", ycsbJarLocation);
        config.save();
        runner = new OracleNosqlRunner(propsFileName, config);
        // Runtime runtime = Runtime.getRuntime();
        // runner.startServer(runType.equals("load"), runtime);

    }

}
