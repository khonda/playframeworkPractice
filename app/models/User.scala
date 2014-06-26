package models

import java.sql.Timestamp

case class User(
                 id:Option[Long],
                 name:String,
                 email: String,
                 password:String,
                 createDate:Option[Timestamp])


