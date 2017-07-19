package to.ogarne.ogarneblog.mockdata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import to.ogarne.ogarneblog.model.Category;
import to.ogarne.ogarneblog.model.Post;
import to.ogarne.ogarneblog.model.User;
import to.ogarne.ogarneblog.service.CategoryService;
import to.ogarne.ogarneblog.service.PostService;
import to.ogarne.ogarneblog.service.UserService;

/**
 * Created by jedrz on 17.07.2017.
 */
@Component
public class DatabaseLoader implements ApplicationRunner {

    @Autowired
    UserService userService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    PostService postService;


    @Override
    public void run(ApplicationArguments args) throws Exception {


        // Add mock users
        User user = new User("jendrew", "password","Jędrzej Kołtunowicz");
        userService.save(user);

        User aniela = new User("anielka","password","Aniela Masna");
        userService.save(aniela);

        // Add mock categories
        Category programowanie = new Category("programowanie", "Programowanie");
        categoryService.save(programowanie);

        Category knigi = new Category("książki", "Książki");
        categoryService.save(knigi);

        Post post1 = new Post("Pierwszy post", "Lorem markdownum aetasque. Tellure mi umida iacentem ignibus *obstructaque*\n" +
                "tofis timet sidus quae. [Unde implevit quadripedis](http://dixerat.com/) prima\n" +
                "armentum suberant innixa fertur *agros*, quaque origine pendat. Enim in gelido\n" +
                "lenius fuerat *nec tutum natumque* sociam potestas adiutis his **accepere**, et\n" +
                "nitenti amens vidit. Gurgite Atlantiades *sonent*, mihi [et\n" +
                "unam](http://regnat.com/nec.html) foliisque Pallade, alumni verba gaudia, esse\n" +
                "miseri undecimus.\n" +
                "\n" +
                "> Temperiemque severa origo purpureum, est tot suos glaciali chlamydem putat.\n" +
                "> Invitus inguine Midae excessisse fallere resupinus depositum accipiunt hominis\n" +
                "> sacrorum miratur. Nec plectro tinxit palmis evincitque *fessa planamque\n" +
                "> titubare* iuppiter pariterque Nemeaeo cadunt. Favus [quem](http://in.net/), a\n" +
                "> quoque pariter [palude pallada](http://nullum.io/).\n" +
                "\n" +
                "Quo annum sum magis pronusque villo aquilone generis, lacrimas e cursu fluminis\n" +
                "**laedor**, tamen. Anno fuerant sitis, nequeam ad artem, nactusque crinem.\n" +
                "Vulnus convicia **abdita** nunc siccaverat haec [venti\n" +
                "aetheris](http://procris.io/frenaira), viso motis.", user, knigi);
        postService.save(post1);

        Post post2 = new Post("Drugi post", "Suis iunxit anus, sum cum Euboicam, ad lacertos. Colorem via Iunonigenaeque\n" +
                "hactenus. Tum horto Alba color multa labor erat alta Aenean lacusque,\n" +
                "[cecidisse](http://sinitpetendi.net/ratis) umbrae turbae oravere, neu hac\n" +
                "[hostilia](http://draconi.net/haec-suadeat). Natura venis superat creber:\n" +
                "pervenit neque, latarumque vocali manibus leviore occupat erant quod pluribus\n" +
                "vidisset hamos requiris anguipedum.\n" +
                "\n" +
                "Vellem et *alis*, alternae, releguntque suam latices oculos Vidi! Ore in coloni\n" +
                "Maeonios. Antrum ait patrias est num, vix, calet forte: et quod tumidus et. Omne\n" +
                "Procris [et equo exercet](http://exhibuit.org/manibus-novitate.aspx) manibus\n" +
                "fluunt, ministros. Muros mora pressa oculos suoque pedibusque liceat, dubium\n" +
                "consurgere est nescia, eget solis umbras.", aniela, programowanie);
        postService.save(post2);

        Post post3 = new Post("Trzeci post", "A nocte, ferox Solis in armaque. Vesta cvrrvs oscula Peleusque sortem vota\n" +
                "puerum *optatos ferinae dubitare* dominaeque rivus vagantur. Et exerces structis\n" +
                "flammaeque obmutuit inponere alis, nos in quae utque, [crebros\n" +
                "in](http://www.regimen-ubi.org/) nec!\n" +
                "\n" +
                "- Gradus in vertice secus\n" +
                "- Intumuit auro rutilis movere geminum colla sacrificat\n" +
                "- Palustribus tamen furentibus toto aptos quod tendens\n" +
                "- Insidiae posse tumulos\n" +
                "- Pari illas\n" +
                "\n" +
                "Illa finire recanduit oculos, quodque bis ad voce\n" +
                "[eris](http://www.iaceres.net/echomagne.html). Ille sub nocebant texit, o carus\n" +
                "cui meos adest, me fata, aviti et. Truncis Troiae esset; *spe* nos, nec carebat\n" +
                "querno, deum Phorcynidos **vulnere puppibus marmor** Pelates et lacrimisque\n" +
                "comas.\n" +
                "\n" +
                "1. Cogeris atque\n" +
                "2. Quam ventus metues positus horrida nemus\n" +
                "3. Moriemur contermina quicquid sortita umeri paludibus planxitque\n" +
                "4. Dat brevis timidis fortuna depositum intraverat tanto", user, knigi);
        postService.save(post3);

        Post post4 = new Post("Czwarty post", "Nostro quae est ille famulae, flamina praeterque tamen vix vastior, cum\n" +
                "**Phrygiis**! Flavescere spatio, tendentemque faciem, palus linguae, vestibus et\n" +
                "natorum moderator, relinquam. Sponte verba, **et tenens** difficile mi iamque\n" +
                "**tum litora tuli**. Et danda gravitate thalamis et haec ab intravit crimen.\n" +
                "Generosaque secura, cornua quae iuncti querellae?\n" +
                "\n" +
                "> Fusus erat motibus quod quae vocatus proles virum veniat uni, ulla. Placet\n" +
                "> alii sed audet, potuit adituque et quarum Cadmi promittere lupi. Unde o\n" +
                "> triones guttae.\n" +
                "\n" +
                "Ad viro cuspis sine minimum cupido nitidumque te pondere quoque, seu Tarpeias.\n" +
                "Pulsa non in ante [votis circueunt vestra](http://alumnovirgo.org/), fide quae\n" +
                "**Acasto**. Postquam felix causa silvis relictum in questus uvae foedumque\n" +
                "cernis nostris neve.", aniela, programowanie);
        postService.save(post4);



    }
}
