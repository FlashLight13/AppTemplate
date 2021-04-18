package com.example.apptemplate.commons.recycler.adapter

import android.os.AsyncTask
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.*
import com.example.apptemplate.arch.ViewBinder
import com.example.apptemplate.arch.ViewHolder

class ViewBinderAdapter(
    private val adapterDataProviders: Map<Int, AdapterDataProvider>,
    diff: Diff? = null,
) :
    RecyclerView.Adapter<ViewBinderAdapter.ViewBinderViewHolder>() {

    private val differ = AsyncListDiffer(
        AdapterListUpdateCallback(this),
        AsyncDifferConfig.Builder(createDiffCallback(diff))
            .setBackgroundThreadExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
            .build()
    )

    fun updateItems(newItems: List<AdapterItem<*>>) {
        differ.submitList(newItems)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewBinderViewHolder {
        val provider = adapterDataProviders.getValue(viewType)
        val view = provider.createView(parent)
        val viewHolder = provider.createViewHolder(view)
        val viewBinder: ViewBinder<in Any?> = provider.getViewBinder()
        return ViewBinderViewHolder(view, viewHolder, viewBinder)
    }

    override fun onBindViewHolder(holder: ViewBinderViewHolder, position: Int) {
        val data: AdapterItem<*> = differ.currentList[position]
        holder.bind(data.data)
    }

    override fun onViewRecycled(holder: ViewBinderViewHolder) {
        holder.unbind()
        super.onViewRecycled(holder)
    }

    override fun onFailedToRecycleView(holder: ViewBinderViewHolder): Boolean {
        holder.unbind()
        return super.onFailedToRecycleView(holder)
    }

    override fun getItemCount(): Int = differ.currentList.size

    companion object {

        private fun createDiffCallback(diff: Diff?): DiffUtil.ItemCallback<AdapterItem<*>> =
            object : DiffUtil.ItemCallback<AdapterItem<*>>() {
                override fun areItemsTheSame(
                    oldItem: AdapterItem<*>,
                    newItem: AdapterItem<*>,
                ): Boolean {
                    if (oldItem.viewType != newItem.viewType) {
                        return false
                    }
                    return diff?.areDataTheSame(oldItem.data, newItem.data, true) ?: false
                }

                override fun areContentsTheSame(
                    oldItem: AdapterItem<*>,
                    newItem: AdapterItem<*>,
                ): Boolean {
                    if (oldItem.viewType != newItem.viewType) {
                        return false
                    }
                    return diff?.areDataTheSame(oldItem.data, newItem.data, false) ?: false
                }
            }
    }

    interface AdapterDataProvider {

        fun createView(parent: ViewGroup): View
        fun createViewHolder(view: View): ViewHolder
        fun getViewBinder(): ViewBinder<in Any?>
    }

    interface Diff {

        fun areDataTheSame(old: Any?, new: Any?, fast: Boolean): Boolean
    }

    class ViewBinderViewHolder(
        view: View,
        private val viewHolder: ViewHolder,
        private val viewBinder: ViewBinder<in Any?>,
    ) : RecyclerView.ViewHolder(view) {

        private var isBound: Boolean = false

        fun bind(data: Any?) {
            if (isBound) {
                return
            }
            viewBinder.bind(viewHolder, data)
            isBound = true
        }

        fun unbind() {
            if (!isBound) {
                return
            }
            viewBinder.unbind(viewHolder)
            isBound = false
        }
    }
}