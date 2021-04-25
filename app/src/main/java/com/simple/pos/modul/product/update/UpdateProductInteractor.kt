package com.simple.pos.modul.product.update

import com.simple.pos.shared.callback.RequestCallback
import com.simple.pos.shared.callback.RetrofitCallback
import com.simple.pos.shared.extension.TAG
import com.simple.pos.shared.model.Category
import com.simple.pos.shared.model.Product
import com.simple.pos.shared.retrofit.ServiceGenerator
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File

class UpdateProductInteractor: UpdateProductContract.Interactor {
    private val service = ServiceGenerator.createService(UpdateProductService::class.java)

    override fun requestUpdateProduct(product: Product, callback: RequestCallback<Product?>) {

        val name: RequestBody = product.name.toRequestBody()
        var picture: MultipartBody.Part? = null
        val total = product.total
        val costPrice = product.costPrice
        val sellingPrice = product.sellingPrice
        val categoryId = product.categoryId

        if(product.picture != null){
            // use the FileUtils to get the actual file by uri
            val file = File(product.picture!!)
            // create RequestBody instance from file
            val requestFile: RequestBody = file.asRequestBody()
            // MultipartBody.Part is used to send also the actual file name
            picture = MultipartBody.Part.createFormData("picture", file.name, requestFile)
        }

        service.updateProduct(
                categoryId, name, picture,  total, sellingPrice, costPrice,
                product.id)
                .enqueue(RetrofitCallback(callback, TAG, "requestUpdateProduct"))
    }

    override fun requestRetrieveCategory(id: Int, callback: RequestCallback<Category>) {
        service.getCategoryName(id).enqueue(RetrofitCallback(callback, TAG, "requestRetrieveCategory"))
    }

}