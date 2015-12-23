/*
Copyright 2015 David R. Pugh, J. Doyne Farmer, and Dan F. Tang

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/
package markets.participants

import akka.actor.Scheduler

import scala.concurrent.duration.FiniteDuration
import scala.concurrent.ExecutionContext


/** Mixin Trait providing behavior necessary to cancel outstanding orders. */
trait OrderCanceler extends MarketParticipant {

  def submitOrderCancellation(): Unit

  /** Schedule order cancellation.
    *
    * @param scheduler
    * @param initialDelay
    * @param executionContext
    */
  def scheduleOrderCancellation(scheduler: Scheduler,
                                initialDelay: FiniteDuration)
                               (implicit executionContext: ExecutionContext): Unit = {
    scheduler.scheduleOnce(initialDelay, self, SubmitOrderCancellation)(executionContext)
  }

  /** Schedule order cancellation
    *
    * @param scheduler
    * @param initialDelay
    * @param interval
    * @param executionContext
    */
  def scheduleOrderCancellation(scheduler: Scheduler,
                                initialDelay: FiniteDuration,
                                interval: FiniteDuration)
                               (implicit executionContext: ExecutionContext): Unit = {
    scheduler.schedule(initialDelay, interval, self, SubmitOrderCancellation)(executionContext)
  }

  override def receive: Receive = {
    case SubmitOrderCancellation => submitOrderCancellation()
    case message => super.receive(message)
  }

  private object SubmitOrderCancellation

}