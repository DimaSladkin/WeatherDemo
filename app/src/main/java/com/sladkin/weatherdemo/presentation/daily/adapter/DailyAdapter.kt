package com.sladkin.weatherdemo.presentation.daily.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sladkin.weatherdemo.R
import com.sladkin.weatherdemo.domain.entity.WeatherModel
import com.sladkin.weatherdemo.extention.getDaily
import com.sladkin.weatherdemo.extention.getDateFromFormat
import com.sladkin.weatherdemo.extention.getResourceForString
import com.sladkin.weatherdemo.extention.parseIsoToDate
import kotlinx.android.synthetic.main.daily_item.view.*

class DailyAdapter(private val context: Context?, val list: List<WeatherModel>)
    : RecyclerView.Adapter<DailyAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return DailyAdapter.ViewHolder(
                LayoutInflater.from(context).inflate(R.layout.daily_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBindView(list[position], context)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun onBindView(weatherModel: WeatherModel, context: Context?) {
            itemView.dateTv.text = weatherModel.date?.getDateFromFormat()?.getDaily()
            itemView.image.setImageResource(getResourceForString(weatherModel.stateAbr))
            itemView.descriptionTv.text = weatherModel.state
            itemView.maxTempTv.text = context?.getString(R.string.temp, weatherModel.maxTemp)
            itemView.minTempTv.text = context?.getString(R.string.temp, weatherModel.minTemp)
        }
    }
}