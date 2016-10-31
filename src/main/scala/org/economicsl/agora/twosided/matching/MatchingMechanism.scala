/*
Copyright 2016 ScalABM

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
package org.economicsl.agora.twosided.matching

import org.economicsl.agora.tradables.orders.ask.AskOrder
import org.economicsl.agora.tradables.orders.bid.BidOrder
import org.economicsl.agora.{onesided, orderbooks}


/** Trait defining the interface for a two-sided `MatchingMechanism`.
  *
  * @tparam A
  * @tparam AB
  * @tparam B
  * @tparam BB
  */
trait MatchingMechanism[A <: AskOrder, -AB <: orderbooks.OrderBookLike[A],
                        B <: BidOrder, -BB <: orderbooks.OrderBookLike[B]] {

  /** `MatchingFunction` used to match an `AskOrder` with an order book containing `BidOrder` instances. */
  def askOrderMatchingFunction: onesided.matching.MatchingFunction[A, BB, B]

  /** `MatchingFunction` used to match a `BidOrder` with an order book containing `AskOrder` instances. */
  def bidOrderMatchingFunction: onesided.matching.MatchingFunction[B, AB, A]

}