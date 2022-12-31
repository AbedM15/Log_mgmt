package com.abc.logManagement.services

import com.abc.logManagement.dto.AllMicroServices
import com.abc.logManagement.dto.CreateMicroService
import com.abc.logManagement.dto.FetchedMicroService
import com.abc.logManagement.dto.MicroServiceCreated
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
      //  logger.info("support eng if any: ${unmappedMicroServices[0].supportEngineers!!.first().emailAddress} ")
        return mapToAllMicroServices(unmappedMicroServices)
    }

    override fun fetchMicroServiceById(id: Long): FetchedMicroService {
        when{
            !repo.findById(id).isPresent -> throw MicroServiceNotFound("Micro service not found")
            else -> {
                var supportEngineers:MutableSet<SupportEngineer>? = mutableSetOf()
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
            all.add(AllMicroServices(microServiceId = m.microServiceId, microServiceName = m.microServiceName, microServiceLogs = m.microServiceLogs, supportEngineers = m.supportEngineers ))
        }
        return all
    }


    fun microServiceSupportEngineers(supportEngineerIds:List<Long>?):MutableSet<SupportEngineer>?{
        var supportEngineers:MutableSet<SupportEngineer>? = mutableSetOf()
        return if(supportEngineerIds.isNullOrEmpty()){
            null
        }else{
            for (m in supportEngineerIds){
                supportEngineers!!.add(engRepo.findById(m).get())
            }
            supportEngineers
        }

    }


}