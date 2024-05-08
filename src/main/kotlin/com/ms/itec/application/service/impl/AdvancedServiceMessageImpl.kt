package com.ms.itec.application.service.impl

import com.ms.itec.application.dto.request.ListOfNumbersImageAndText
import com.ms.itec.application.dto.request.MessageWithImageAdvancedDto
import com.ms.itec.application.dto.request.ProspectModelDto
import com.ms.itec.application.service.AdvancedServiceMessage
import com.ms.itec.application.service.AdvancedServiceMessageWithImage
import com.ms.itec.presentation.excepetion.NotPossibleSendTheMessageForNumbers
import com.ms.itec.presentation.mapper.FromDto
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@Service
class AdvancedServiceMessageImpl(private val advancedServiceMessage: AdvancedServiceMessage, private val advancedServiceMessageImg: AdvancedServiceMessageWithImage){
    fun sendMessage(prospectModelDto: ProspectModelDto){
        val messageAdvancedDto = FromDto().toMessageAdvancedDto(prospectModelDto)
        print(advancedServiceMessage.sendMessage(messageAdvancedDto))
    }

    fun sendMessageWithImage(listData: ListOfNumbersImageAndText): MutableList<MessageWithImageAdvancedDto>{

        val listOfMessagesReadyTOSend: MutableList<MessageWithImageAdvancedDto> = mutableListOf()

        listData.numbers.forEach { number ->
            val messageWithImage = FromDto().toMessageWithImageAdvancedDto(listData.image, listData.text, number)
            listOfMessagesReadyTOSend.add(messageWithImage)
        }
        try {

            listOfMessagesReadyTOSend.forEach{
                    messageWithImage-> advancedServiceMessageImg.sendMessageWithImage(
                        messageWithImage.number,
                        messageWithImage.openTicket,
                        messageWithImage.queueId,
                        messageWithImage.body,
                        messageWithImage.image
                    )
            }

            return listOfMessagesReadyTOSend

        }catch (e: NotPossibleSendTheMessageForNumbers){
            throw NotPossibleSendTheMessageForNumbers(
                e.toString(),
                e.cause
            )
        }



    }
    fun convertMultipartFileToBinary(file: MultipartFile): ByteArray {
        return file.bytes
    }

}