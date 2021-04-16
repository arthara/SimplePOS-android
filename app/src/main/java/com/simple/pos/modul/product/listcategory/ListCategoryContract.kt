
import com.simple.pos.modul.product.listcategory.model.ListCategory
import com.simple.pos.shared.callback.RequestCallback
import com.simple.pos.shared.model.Category


interface ListCategoryContract {
    interface View {
        fun showCategories(categoryLists: Array<Category>)
        fun redirectToCreateProduct()
    }

    interface Presenter {
        fun retrieveCategories()
    }

    interface Interactor {
        fun requestRetrieveCategories(requestCallback: RequestCallback<Array<Category>>)
        fun requestRetrieveCategoriesWithCounts(requestCallback: RequestCallback<Array<ListCategory>>)
    }
}