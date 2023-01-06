package com.abc.logManagement.services

import com.abc.logManagement.dto.*
import com.abc.logManagement.entities.MicroService
import com.abc.logManagement.entities.SupportEngineer
import com.abc.logManagement.exceptions.MicroServiceBadRequest
import com.abc.logManagement.exceptions.MicroServiceNotFound
import com.abc.logManagement.repositories.MicroServicesRepository
import com.abc.logManagement.repositories.SupportEngineersRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.Optional

@Service
class MicroServiceServiceImpl:MicroServiceService {

    @Autowired
    lateinit var repo:MicroServicesRepository

    @Autowired
    lateinit var engRepo:SupportEngineersRepository

    var logger: Logger = LoggerFactory.getLogger(MicroServiceServiceImpl::class.java)

    override fun createMicroService(microService: CreateMicroService): MicroServiceCreated {
        when {
            microService.name.isBlank() -> throw MicroServiceBadRequest("Micro service name cannot be empty")
            else-> {
                when{
                    repo.findMicroServiceByName(microService.name)>=1 -> throw MicroServiceBadRequest("Micro service already exists")
                    else -> {
                        val toBeSaved = MicroService(microServiceId = null, microServiceName = microService.name, supportEngineers = null)
                        val saved = repo.save(toBeSaved)
                        return  MicroServiceCreated(microServiceId = saved.microServiceId, microServiceName = saved.microServiceName)
                    }
                }
            }
        }
    }
    override fun fetchAllMicroServices(): MutableList<AllMicroServices>? {
        val unmappedMicroServices: MutableList<MicroService> = repo.findAll()

        return mapToAllMicroServices(unmappedMicroServices)
    }

    override fun fetchMicroServiceById(id: Long): FetchedMicroService {
        when{
            !repo.findById(id).isPresent -> throw MicroServiceNotFound("Micro service not found")
            else -> {
                val supportEngineers:MutableSet<MicroServiceByIdSupportEngineer>?
                val fetched = repo.findById(id).get()
                val supportEngineerIds:List<Long>? = repo.findSupportEngineerIdsByMicroServiceId(id)
                supportEngineers = microServiceSupportEngineers(supportEngineerIds)
                return FetchedMicroService(microServiceId = fetched.microServiceId, microServiceName = fetched.microServiceName, microServiceLogs = fetched.microServiceLogs, supportEngineers = supportEngineers)
            }
        }
    }


    fun mapToAllMicroServices(microServices:MutableList<MicroService>):MutableList<AllMicroServices>{
        val all = mutableListOf<AllMicroServices>()
        for (m in microServices){
            var suppEng:MutableSet<MicroServiceByIdSupportEngineer>?
            val supportEngineerIds:List<Long>? = repo.findSupportEngineerIdsByMicroServiceId(m.microServiceId!!)
            suppEng = microServiceSupportEngineers(supportEngineerIds)
            all.add(AllMicroServices(microServiceId = m.microServiceId, microServiceName = m.microServiceName, microServiceLogs = m.microServiceLogs, supportEngineers = suppEng))
        }
        return all
    }


    fun microServiceSupportEngineers(supportEngineerIds:List<Long>?):MutableSet<MicroServiceByIdSupportEngineer>?{
        val supportEngineers:MutableSet<MicroServiceByIdSupportEngineer>? = mutableSetOf()
        var temp:SupportEngineer
        return if(supportEngineerIds.isNullOrEmpty()){
            null
        }else{
            for (m in supportEngineerIds){
                temp = engRepo.findById(m).get()
                supportEngineers!!.add(
                    MicroServiceByIdSupportEngineer(id = temp.id, firstName = temp.firstName,
                    lastName = temp.lastName, emailAddress = temp.emailAddress
                )
                )
            }
            supportEngineers
        }

    }






}