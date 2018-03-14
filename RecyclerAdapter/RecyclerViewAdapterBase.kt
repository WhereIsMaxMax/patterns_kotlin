package 

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup


abstract class RecyclerViewAdapterBase<D, V> : RecyclerView.Adapter<ViewWrapper<D, V>>() where V : View, V : ViewWrapper.Binder<D> {

    private val items = ArrayList<D>()
    private var listener: OnItemClickListener<D, V>? = null


    fun setOnItemClickListener(listener: OnItemClickListener<D, V>) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewWrapper<D, V> {
        return ViewWrapper(onCreateItemView(parent, viewType))
    }

    protected abstract fun onCreateItemView(parent: ViewGroup, viewType: Int): V

    override fun onBindViewHolder(viewHolder: ViewWrapper<D, V>, position: Int) {
        val view = viewHolder.view
        val data = items[position]
        view.bind(data)
        val p = viewHolder.adapterPosition
        view.setOnClickListener({ onItemClick(p, view, data) })
    }

    private fun onItemClick(position: Int, view: V, data: D) {
        listener?.onItemClick(position, view, data)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun add(item: D) {
        items.add(item)
        notifyDataSetChanged()
    }

    fun addAll(collection: Collection<D>) {
        items.addAll(collection)
        notifyDataSetChanged()
    }

    fun clear() {
        items.clear()
        notifyDataSetChanged()
    }

    fun getItems(): List<D> {
        return items
    }

    interface OnItemClickListener<in D, in V> {
        fun onItemClick(position: Int, view: V, data: D)
    }

}