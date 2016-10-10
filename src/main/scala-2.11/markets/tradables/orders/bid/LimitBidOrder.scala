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
package markets.tradables.orders.bid

import java.util.UUID

import markets.tradables.orders.ask.AskOrder
import markets.tradables.orders.Predicate
import markets.tradables.Tradable

/**
  *
  * @param issuer
  * @param price
  * @param quantity
  * @param timestamp
  * @param tradable
  * @param uuid
  */
case class LimitBidOrder(issuer: UUID, price: Long, quantity: Long, timestamp: Long, tradable: Tradable, uuid: UUID)
  extends BidOrder {

  require(price > 0, "price of a LimitBidOrder must be strictly positive.")

  override val isAcceptable: (AskOrder) => Boolean = super.isAcceptable

}
