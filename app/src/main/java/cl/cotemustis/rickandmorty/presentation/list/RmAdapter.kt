package cl.cotemustis.rickandmorty.presentation.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import cl.cotemustis.rickandmorty.R
import cl.cotemustis.rickandmorty.data.model.CharacterData
import com.bumptech.glide.RequestManager
import javax.inject.Inject


class RmAdapter @Inject constructor(
    private val glide: RequestManager
) : RecyclerView.Adapter<RmAdapter.ViewHolder>() {

    private val diffCallback = object : DiffUtil.ItemCallback<CharacterData>() {
        override fun areItemsTheSame(oldItem: CharacterData, newItem: CharacterData): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: CharacterData, newItem: CharacterData): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)

    var characters: List<CharacterData>
        get() = differ.currentList
        set(value) = differ.submitList(value)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_character, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val character = characters[position]
        holder.itemName.text = character.name
        holder.itemLocation.text = holder.itemView.context.getString(
            R.string.item_location_placeholder,
            character.location?.name ?: ""
        )

        holder.itemStatus.text = holder.itemView.context.getString(
            R.string.item_status_placeholder,
            character.status
        )

        holder.itemImage.apply {
            glide.load(character.image).into(this)
        }
    }

    override fun getItemCount(): Int {
        return characters.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemName: TextView = itemView.findViewById(R.id.itemCharacterName)
        val itemLocation: TextView = itemView.findViewById(R.id.itemCharacterLocation)
        val itemStatus: TextView = itemView.findViewById(R.id.itemCharacterStatus)
        val itemImage: ImageView = itemView.findViewById(R.id.itemCharacterImage)
    }
}