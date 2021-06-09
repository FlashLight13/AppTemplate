package com.example.apptemplate.commons.recycler.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class ViewBinderAdapter(
    dataItemCallback: DiffUtil.ItemCallback<Any>,
    mainDispatcher: CoroutineDispatcher = Dispatchers.Main,
    workerDispatcher: CoroutineDispatcher = Dispatchers.Default,
    private val viewFactories: Map<Int, ViewFactory>,
) : PagingDataAdapter<AdapterItem<out Any>, ViewBinderAdapter.ViewBinderViewHolder>(
    AdapterItemCallback(dataItemCallback),
    mainDispatcher,
    workerDispatcher,
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewBinderViewHolder {
        val factory: ViewFactory = viewFactories.getValue(viewType)
        val viewBinding: ViewBinding = factory.createView(parent)
        val viewBinder: ViewBinder<in ViewBinding, in Any?> = factory.createViewBinder()
        return ViewBinderViewHolder(viewBinding, viewBinder)
    }

    override fun onBindViewHolder(holder: ViewBinderViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it.data)
        }
    }

    override fun onViewRecycled(holder: ViewBinderViewHolder) {
        holder.unbind()
        super.onViewRecycled(holder)
    }

    override fun onFailedToRecycleView(holder: ViewBinderViewHolder): Boolean {
        holder.unbind()
        return super.onFailedToRecycleView(holder)
    }

    override fun getItemViewType(position: Int): Int = getItem(position)!!.viewType

    private class AdapterItemCallback(private val delegate: DiffUtil.ItemCallback<Any>) :
        DiffUtil.ItemCallback<AdapterItem<out Any>>() {

        override fun areItemsTheSame(
            oldItem: AdapterItem<out Any>,
            newItem: AdapterItem<out Any>
        ): Boolean {
            if (oldItem.viewType == newItem.viewType) {
                return delegate.areItemsTheSame(oldItem.data, newItem.data)
            }
            return false
        }

        override fun areContentsTheSame(
            oldItem: AdapterItem<out Any>,
            newItem: AdapterItem<out Any>
        ): Boolean {
            if (oldItem.viewType == newItem.viewType) {
                return delegate.areContentsTheSame(oldItem.data, newItem.data)
            }
            return false
        }
    }

    interface ViewFactory {

        fun createView(parent: ViewGroup): ViewBinding
        fun createViewBinder(): ViewBinder<in ViewBinding, in Any?>
    }

    class ViewBinderViewHolder(
        private val viewBinding: ViewBinding,
        private val viewBinder: ViewBinder<in ViewBinding, in Any?>,
    ) : RecyclerView.ViewHolder(viewBinding.root) {

        private var isBound: Boolean = false

        fun bind(data: Any) {
            if (isBound) {
                viewBinder.unbind(viewBinding)
            }
            viewBinder.bind(viewBinding, data)
            isBound = true
        }

        fun unbind() {
            if (!isBound) {
                return
            }
            viewBinder.unbind(viewBinding)
            isBound = false
        }
    }
}