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
package org.economicsl.agora.generics.orderbooks.immutable

import org.economicsl.agora.generics
import org.economicsl.agora.tradables.orders.Order

import scala.collection.immutable


/** Mixin trait defining the interface for a `SortedOrderBook`.
  *
  * @tparam O the type of `Order` stored in a `SortedOrderBook`.
  */
trait SortedOrders[O <: Order] extends generics.orderbooks.SortedOrders[O, immutable.TreeSet[O]]{
  this: generics.orderbooks.OrderBookLike[O] =>

}

