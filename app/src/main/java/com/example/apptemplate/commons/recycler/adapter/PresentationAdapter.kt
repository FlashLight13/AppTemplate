package com.example.apptemplate.commons.recycler.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.apptemplate.commons.view.Presentation
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class PresentationAdapter<T : Any>(
    dataItemCallback: DiffUtil.ItemCallback<T>,
    mainDispatcher: CoroutineDispatcher = Dispatchers.Main,
    workerDispatcher: CoroutineDispatcher = Dispatchers.Default,
    private val viewFactories: Map<Int, ViewFactory>,
) : PagingDataAdapter<T, PresentationAdapter.PresentationViewHolder>(
    dataItemCallback,
    mainDispatcher,
    workerDispatcher,
) {

    @Suppress("unchecked_cast")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PresentationViewHolder {
        val factory: ViewFactory = viewFactories.getValue(viewType)
        val viewBinding: ViewBinding = factory.createView(parent)
        val presentation: Presentation<in Any, in ViewBinding> =
            factory.createViewBinder() as Presentation<in Any, in ViewBinding>
        return PresentationViewHolder(viewBinding, presentation)
    }

    override fun onBindViewHolder(holder: PresentationViewHolder, position: Int) {
        getItem(position)?.let {
            holder.attach(it)
        } ?: holder.detach()
    }

    override fun onViewRecycled(holder: PresentationViewHolder) {
        holder.detach()
        super.onViewRecycled(holder)
    }

    override fun onFailedToRecycleView(holder: PresentationViewHolder): Boolean {
        holder.detach()
        return super.onFailedToRecycleView(holder)
    }

    override fun getItemViewType(position: Int): Int = getItem(position)!!::class.hashCode()

    interface ViewFactory {

        fun createView(parent: ViewGroup): ViewBinding
        fun createViewBinder(): Presentation<out Any, out ViewBinding>
    }

    class PresentationViewHolder(
        private val viewBinding: ViewBinding,
        private val presentation: Presentation<in Any, in ViewBinding>,
    ) : RecyclerView.ViewHolder(viewBinding.root) {

        private var isAttached: Boolean = false

        fun attach(data: Any) {
            if (isAttached) {
                presentation.detach(viewBinding)
            }
            presentation.attach(viewBinding, data)
            isAttached = true
        }

        fun detach() {
            if (!isAttached) {
                return
            }
            presentation.detach(viewBinding)
            isAttached = false
        }
    }
}