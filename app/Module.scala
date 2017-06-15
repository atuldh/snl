import com.google.inject.AbstractModule
import repository.SNLRepository
import services.{PlayerService, PlayerServiceImpl}

class Module extends AbstractModule {

  override def configure() = {
    bind(classOf[PlayerService]).to(classOf[PlayerServiceImpl]).asEagerSingleton()
    bind(classOf[SNLRepository])
  }
}
