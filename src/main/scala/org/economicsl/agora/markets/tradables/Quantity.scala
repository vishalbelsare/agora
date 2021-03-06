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
package org.economicsl.agora.markets.tradables


sealed trait Quantity {
  this: Tradable =>

  def quantity: Long

}


trait SingleUnit extends Quantity {
  this: Tradable =>

  val quantity = 1

}


trait MultiUnit extends Quantity {
  this: Tradable =>

  require(quantity > 0, "Quantity must be strictly positive.")

}
