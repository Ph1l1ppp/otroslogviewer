/*******************************************************************************
 * Copyright 2011 Krzysztof Otrebski
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package pl.otros.logview.reader;

import pl.otros.logview.LogData;
import pl.otros.logview.LogDataCollector;

import java.util.LinkedList;

public class ProxyLogDataCollector implements LogDataCollector {

  private final LinkedList<LogData> list;

  public ProxyLogDataCollector() {
    list = new LinkedList<>();
  }

  public void add(LogData... logDatas) {
    for (LogData logData : logDatas) {
      list.addLast(logData);
    }

  }

  public LogData[] getLogData() {
    LogData[] datas = new LogData[list.size()];
    datas = list.toArray(datas);
    return datas;
  }

  @Override
  public int clear() {
    int size = list.size();
    list.clear();
    return size;
  }

}
