package com.abc.logManagement.services

import com.abc.logManagement.dto.SupportEngineerCreate
import com.abc.logManagement.dto.SupportEngineerCreated
import com.abc.logManagement.entities.MicroService
import com.abc.logManagement.entities.SupportEngineer
import com.abc.logManagement.exceptions.SupportEngineerBadRequest
import com.abc.logManagement.repositories.MicroServicesRepository
import com.abc.logManagement.repositories.SupportEngineersRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service



@Service
class SupportEngineerServiceImpl:SupportEngineerService {
    val EMAIL_REGEX = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})";

    @Autowired
    lateinit var repo:SupportEngineersRepository

    @Autowired
    lateinit var microServicesRepo:MicroServicesRepository

    override fun createSupportEngineer(supportEngineer: SupportEngineerCreate): SupportEngineerCreated {
        when{
            supportEngineer.firstName.isBlank() -> throw SupportEngineerBadRequest("First Name field cannot be blank")
            supportEngineer.lastName.isBlank() -> throw SupportEngineerBadRequest("Last Name field cannot be blank")
            supportEngineer.emailAddress.isBlank() -> throw SupportEngineerBadRequest("Email Address field cannot be blank")
            !isEmailValid(supportEngineer.emailAddress) -> throw SupportEngineerBadRequest("Email entered is not a valid address")
            supportEngineer.microServices.isNullOrEmpty()  -> throw SupportEngineerBadRequest("Micro service id is required inorder to create a support engineer")
            repo.findEngineerByEmail(supportEngineer.emailAddress) >=1 -> throw SupportEngineerBadRequest("Support engineer with email ${supportEngineer.emailAddress} already exists")

            else-> {
                val microServices = mutableSetOf<MicroService>()
                val finalMicroServiceSet = mutableSetOf<MicroService>()
                for(m in supportEngineer.microServices!!){
                   microServices.add(m)
                }

                for(m in microServices){
                    if(!microServicesRepo.findById(m.microServiceId!!).isPresent){
                        throw SupportEngineerBadRequest("Micro service with id ${m.microServiceId} does not exist")
                    }else{
                        finalMicroServiceSet.add(microServicesRepo.findById(m.microServiceId!!).get())
                    }
                }

                val toBeSaved = SupportEngineer(id = null, firstName = supportEngineer.firstName, lastName = supportEngineer.lastName, emailAddress = supportEngineer.emailAddress,microServices = finalMicroServiceSet)
                val saved = repo.save(toBeSaved)
                return SupportEngineerCreated(id = saved.id, firstName = saved.firstName, lastName = saved.lastName, emailAddress = saved.emailAddress, microServices = saved.microServices )
            }
        }
    }




    fun isEmailValid(email: String): Boolean {
        return EMAIL_REGEX.toRegex().matches(email)
    }

}