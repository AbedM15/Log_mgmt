package com.abc.logManagement.services

import com.abc.logManagement.entities.SupportEngineer
import com.abc.logManagement.exceptions.InvalidEmailAddress
import com.abc.logManagement.exceptions.SupportEngineerAlreadyExists
import com.abc.logManagement.exceptions.SupportEngineerDoesNotExist
import com.abc.logManagement.exceptions.SupportEngineerRequiredField
import com.abc.logManagement.repositories.SupportEngineersRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class SupportEngineersServiceImpl: SupportEngineersService {

    @Autowired
    lateinit var repositoryLayerCall:SupportEngineersRepository

    val EMAIL_REGEX = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})";
    fun isEmailValid(email: String): Boolean {
        return EMAIL_REGEX.toRegex().matches(email);
    }



    override fun addSupportEngineer(supportEngineer: SupportEngineer): SupportEngineer {

        when{
            supportEngineer.emailAddress.isBlank() -> throw SupportEngineerRequiredField("Support engineer email cannot be empty")
            supportEngineer.firstName.isBlank() -> throw SupportEngineerRequiredField("Support engineer first name cannot be empty")
            supportEngineer.lastName.isBlank() -> throw SupportEngineerRequiredField("Support engineer last name cannot be empty")
        }

        if(!isEmailValid(supportEngineer.emailAddress)){
            throw InvalidEmailAddress("Support engineer email is not a valid address")
        }

        if (repositoryLayerCall.supportEngineerExists(supportEngineer.emailAddress) >= 1){
            throw SupportEngineerAlreadyExists("Support engineer already exists")
        }else{
            return repositoryLayerCall.save(supportEngineer)
        }

    }

    override fun deleteSupportEngineerById(id: Long) {
        if(!repositoryLayerCall.findById(id).isPresent){
            throw SupportEngineerDoesNotExist("Support engineer of id $id does not exist ")
        }else{
            return repositoryLayerCall.deleteById(id)
        }
    }

    override fun deleteSupportEngineerByEmail(email: String) {

        if (repositoryLayerCall.supportEngineerExists(email) >= 1){
            return repositoryLayerCall.deleteSupportEngineerByEmail(email)
        }else{
            throw SupportEngineerDoesNotExist("Support engineer of email $email does not exist")
        }
    }

    override fun getAll(): List<SupportEngineer> {
        return repositoryLayerCall.findAll()
    }

    override fun getSupportEngineerById(id: Long): SupportEngineer {
        if (repositoryLayerCall.findById(id).isPresent){
            return repositoryLayerCall.findById(id).get()
        }else{
            throw SupportEngineerDoesNotExist("Support Engineer of id $id does not exist")
        }

    }

    override fun getSupportEngineerByEmail(email: String): SupportEngineer {
       if(email.isBlank()){
           throw SupportEngineerRequiredField("Email address is a required field")
       }else{

          if (repositoryLayerCall.supportEngineerExists(email)>=1){
              return repositoryLayerCall.getSupportEngineerByEmail(email)
          }else{
              throw SupportEngineerDoesNotExist("Support Engineer of email $email does not exist")
          }

       }
    }


}