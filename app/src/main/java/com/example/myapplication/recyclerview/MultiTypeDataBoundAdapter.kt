package com.example.myapplication.recyclerview

import java.lang.IllegalArgumentException

import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import com.example.myapplication.*
import java.util.ArrayList
import java.util.Arrays
import java.util.Collections


/**
 * An abstraction over [BaseDataBoundAdapter] that keeps the list of children and can
 * support multiple item types.
 *
 *
 * This class relies on all layouts using the "data" variable to represent the item.
 *
 *
 * Although this class by itself just exists for demonstration purposes, it might be a useful idea
 * for an application to have a generic naming convention for their items to scale lists with
 * many view types.
 *
 *
 * Note that, by using this, you lose the compile time type checking for mapping your items to
 * layout files but at run time, it will still be checked. See
 * [androidx.databinding.ViewDataBinding.setVariable] for details.
 */
open class MultiTypeDataBoundAdapter(private val mActionCallback: ActionCallback, vararg items: Any) : BaseDataBoundAdapter<ViewDataBinding>() {

    private val mItems = ArrayList<Any>()

    protected open val items: List<Any>
        get() = mItems

    init {
        if (null != items) {
            Collections.addAll(mItems, *items)
        }
    }

    override fun bindItem(holder: DataBoundViewHolder<ViewDataBinding>, position: Int, payloads: List<Any>) {
        val item = mItems[position]

        holder.binding.setVariable(BR.data, mItems[position])
//        // this will work even if the layout does not have a callback parameter
        holder.binding.setVariable(BR.callback, mActionCallback)
        if (item is DynamicBinding) {
            item.bind(holder)
        }
    }

    @LayoutRes
    override fun getItemLayoutId(position: Int): Int {
        // use layout ids as types
        val item = getItem(position)

        if (item is LayoutBinding) {
            return item.getLayoutId()
        }
        if (BuildConfig.DEBUG) {
            throw IllegalArgumentException("unknown item type " + item!!)
        }
        return -1
    }

    override fun getItemCount(): Int {
        return mItems.size
    }

    fun getItem(position: Int): Any? {
        return if (position < mItems.size) mItems[position] else null
    }

    fun indexOf(item: Any): Int {
        return mItems.indexOf(item)
    }

    fun addItem(item: Any) {
        mItems.add(item)
        notifyItemInserted(mItems.size - 1)
    }

    fun addItem(position: Int, item: Any) {
        mItems.add(position, item)
        notifyItemInserted(position)
    }

    fun setItems(vararg items: Any) {
        mItems.clear()
        if (null != items) {
            Collections.addAll(mItems, *items)
        }
        notifyDataSetChanged()
    }

    fun addItems(vararg items: Any) {
        if (null != items) {
            val start = mItems.size
            Collections.addAll(mItems, *items)
            notifyItemRangeChanged(start, items.size)
        }
    }

    fun removeItem(item: Any) {
        val position = mItems.indexOf(item)
        if (position >= 0) {
            mItems.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    fun removeItems(vararg items: Any) {
        if (null != items) {
            val size = mItems.size
            mItems.removeAll(Arrays.asList(*items))
            notifyItemRangeChanged(0, size)
        }
    }

    fun clear() {
        val size = mItems.size
        mItems.clear()
        notifyItemRangeRemoved(0, size)
    }

    /**
     * Class that all action callbacks must extend for the adapter callback.
     */
    interface ActionCallback

}
