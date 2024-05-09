package com.ms.itec.application.service.impl

import com.ms.itec.application.dto.request.MessageWithImageAdvancedDto
import com.ms.itec.application.dto.request.ProspectModelDto
import com.ms.itec.application.service.AdvancedServiceMessage
import com.ms.itec.presentation.excepetion.NotPossibleSendTheMessageForNumbers
import com.ms.itec.presentation.mapper.FromDto
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@Service
class AdvancedServiceMessageImpl(
    private val advancedServiceMessage: AdvancedServiceMessage,
    private val advancedServiceMessageImg: AdvancedServiceMessageWithImage
){

    fun sendMessage(prospectModelDto: ProspectModelDto){
        val messageAdvancedDto = FromDto().toMessageAdvancedDto(prospectModelDto)
        print(advancedServiceMessage.sendMessage(messageAdvancedDto))
    }

    fun sendMessageWithImage(listData: List<String>, image:MultipartFile, text: String): MutableMap<String, String>{


        val listOfMessagesResume: MutableMap<String, String> = mutableMapOf()
        val listOfMessagesReadyToSend: MutableList<MessageWithImageAdvancedDto> = mutableListOf()

        listData.forEach { number ->
            val messageWithImage = FromDto().toMessageWithImageAdvancedDto(image,text, number)
            listOfMessagesReadyToSend.add(messageWithImage)
        }
        try {
            listOfMessagesReadyToSend.forEach{ messageWithImage -> println(messageWithImage)}

            listOfMessagesReadyToSend.forEach{ messageWithImage ->
                val response = advancedServiceMessageImg.sendMessage(
                    img = messageWithImage.medias,
                    number = messageWithImage.number,
                    message= messageWithImage.body,
                    openTicket = messageWithImage.openTicket,
                    queueId = messageWithImage.queueId

                )

                listOfMessagesResume["Response for :"+ messageWithImage.number] = response


            }

            return listOfMessagesResume

        }catch (e: NotPossibleSendTheMessageForNumbers){
            throw NotPossibleSendTheMessageForNumbers(
                e.toString(),
                e.cause
            )
        }

    }


}