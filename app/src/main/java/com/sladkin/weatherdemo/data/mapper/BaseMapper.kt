package com.sladkin.weatherdemo.data.mapper

abstract class BaseMapper<Api, Db, Domain> {

    abstract fun apiModelToDomain(apiModel: Api): Domain

    abstract fun apiModelToDb(apiModel: Api): Db

    abstract fun dbModelToDomain(dbModel: Db): Domain
}