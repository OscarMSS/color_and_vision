package com.example.colorsandvision.BasedeDatos

import java.sql.Connection
import java.sql.DriverManager

class MySQLConnector(
    private val url: String,
    private val user: String,
    private val password: String
) {

    init {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver")
        } catch (e: ClassNotFoundException) {
            e.printStackTrace()
        }
    }

    fun getData(query: String): List<String> {
        val dataList = mutableListOf<String>()

        try {
            val connection: Connection = DriverManager.getConnection(url, user, password)
            val statement = connection.createStatement()
            val resultSet = statement.executeQuery(query)

            while (resultSet.next()) {
                val columnValue = resultSet.getString("your_column_name")
                dataList.add(columnValue)
            }

            resultSet.close()
            statement.close()
            connection.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return dataList
    }
}