
import com.simple.pos.modul.product.listcategory.ListCategory
import com.simple.pos.shared.callback.RequestCallback
import com.simple.pos.shared.model.Category


interface ListCategoryContract {
    interface View {
        fun showCategories(categoryLists: Array<ListCategory>)
        fun redirectToCreateProduct()
    }

    interface Presenter {
        fun retrieveCategories()
        fun retrieveCategoriesWithCounts()
    }

    interface Interactor {
        fun requestRetrieveCategories(requestCallback: RequestCallback<Array<Category>>)
        fun requestRetrieveCategoriesWithCounts(requestCallback: RequestCallback<Array<ListCategory>>)
    }
}