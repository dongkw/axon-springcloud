//package com.example;
//
//import io.swagger.annotations.ApiModelProperty
//import lombok.Data
//import org.axonframework.modelling.command.TargetAggregateIdentifier
//
//data class CreateCmd(@TargetAggregateIdentifier var id: String? = null, var data: String? = null)
//
//
////data class DistributeCmd(var a: String) : DomianCmd(a) ;
//
//open class DistributeCmd(open val element: Istr) : DomainCmd()
//
//
//data class BondDistributeCmd(override val element: Bond) : DistributeCmd(element)
//
//
//open class DomainCmd() {
//    var aggregateId: String? = null
//    var regId: String? = null
//    var requestId: String? = null
//    var times: Long = System.currentTimeMillis();
//
//
//    fun set(aggregateId: String, regId: String?, requestId: String?) {
//        this.aggregateId = aggregateId
//        this.regId = regId
//        this.requestId = requestId;
//    }
//}
//
//
//open class Istr() {
//    var instructionID: String? = null
//
//    @ApiModelProperty(value = "指令价格", required = true, position = 8)
//    var price: String? = null
//
//    @ApiModelProperty(value = "指令总量", required = true, position = 9)
//    var volume = 0L
//
//    @ApiModelProperty(value = "指令金额", required = true, position = 10)
//    var amount: String? = null
//
//    @ApiModelProperty(value = "交易日期", required = true, position = 16)
//    var tradeDate: String? = null
//    override fun toString(): String {
//        return "Istr(instructionID=$instructionID, price=$price, volume=$volume, amount=$amount, tradeDate=$tradeDate)"
//    }
//
//
//}
//
//open class Bond() : Istr() {
//    var bondCode: String? = null;
//    var bondName: String? = null;
//    override fun toString(): String {
//        return "Bond(bondCode=$bondCode, bondName=$bondName)" + super.toString();
//    }
//
//
//}