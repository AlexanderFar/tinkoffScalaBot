package main.scala

import org.telegram.telegrambots.TelegramApiException
import org.telegram.telegrambots.api.methods.send.SendMessage
import org.telegram.telegrambots.api.objects.{Message, Update}
import org.telegram.telegrambots.bots.TelegramLongPollingBot

/**
  * Created by AFarashutdinov on 07.08.2016.
  */

class TinkoffBotHandler extends TelegramLongPollingBot with BotSettings {

  override def onUpdateReceived(update: Update): Unit = {

    //check if the update has a message
    if (update.hasMessage()) {
      val message = update.getMessage

      //check if the message has text. it could also  contain for example a location ( message.hasLocation() )
      if (message.hasText) {
        val answer = CommandFactory.getCommand(message.getText).getAnswer


        //create a object that contains the information to send back the message
        val sendMessageRequest = new SendMessage
        //who should get the message? the sender from which we got the message...
        sendMessageRequest.setChatId(message.getChatId.toString)
        sendMessageRequest.setText(answer)
        //        try {
        sendMessage(sendMessageRequest); //at the end, so some magic and send the message ;)
        //        } catch  {
        //          case e:TelegramApiException =>
        //do some error handling
        //        } //end catch()
      } //end if()
    } //end  if()
  }
}



