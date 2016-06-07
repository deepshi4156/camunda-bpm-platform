/* Licensed under the Apache License, Version 2.0 (the "License");
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
package org.camunda.bpm.engine.impl.runtime;

import org.camunda.bpm.engine.impl.persistence.entity.ExecutionEntity;
import org.camunda.bpm.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.camunda.bpm.engine.repository.ProcessDefinition;
import org.camunda.bpm.engine.runtime.Execution;
import org.camunda.bpm.engine.runtime.MessageCorrelationResult;

/**
 * <p>The result of a message correlation. A message may be correlated to either
 * a waiting execution (BPMN receive message event) or a process definition
 * (BPMN message start event). The type of the correlation (execution vs.
 * processDefinition) can be obtained using {@link #getResultType()}</p>
 *
 * <p>Correlation is performed by a {@link CorrelationHandler}.</p>
 *
 * @author Daniel Meyer
 *
 */
public class MessageCorrelationResultImpl implements MessageCorrelationResult {

  /** signifies a message correlated to an execution */
  public final static String TYPE_EXECUTION = "execution";

  /** signifies a message correlated to a process definition */
  public final static String TYPE_PROCESS_DEFINITION = "processDefinition";

  /**
   * @see MessageCorrelationResultImpl#TYPE_EXECUTION
   * @see MessageCorrelationResultImpl#TYPE_PROCESS_DEFINITION
   */
  protected String resultType;

  protected ExecutionEntity executionEntity;

  protected ProcessDefinitionEntity processDefinitionEntity;

  protected String startEventActivityId;

  public static MessageCorrelationResultImpl matchedExecution(ExecutionEntity executionEntity) {
    MessageCorrelationResultImpl messageCorrelationResult = new MessageCorrelationResultImpl();
    messageCorrelationResult.resultType = TYPE_EXECUTION;
    messageCorrelationResult.executionEntity = executionEntity;
    return messageCorrelationResult;
  }

  public static MessageCorrelationResultImpl matchedProcessDefinition(ProcessDefinitionEntity processDefinitionEntity, String startEventActivityId) {
    MessageCorrelationResultImpl messageCorrelationResult = new MessageCorrelationResultImpl();
    messageCorrelationResult.resultType = TYPE_PROCESS_DEFINITION;
    messageCorrelationResult.processDefinitionEntity = processDefinitionEntity;
    messageCorrelationResult.startEventActivityId = startEventActivityId;
    return messageCorrelationResult;
  }

  // getters ////////////////////////////////////////////

  public ExecutionEntity getExecutionEntity() {
    return executionEntity;
  }

  public ProcessDefinitionEntity getProcessDefinitionEntity() {
    return processDefinitionEntity;
  }

  @Override
  public String getStartEventActivityId() {
    return startEventActivityId;
  }

  @Override
  public String getResultType() {
    return resultType;
  }

  @Override
  public Execution getExecution() {
    return executionEntity;
  }

  @Override
  public ProcessDefinition getProcessDefinition() {
    return processDefinitionEntity;
  }

}
