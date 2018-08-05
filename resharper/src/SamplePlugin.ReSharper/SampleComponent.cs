using JetBrains.ProjectModel;
using JetBrains.ReSharper.Psi;
#if RIDER
using JetBrains.ReSharper.Host.Features;
using JetBrains.Rider.Model;
#endif

namespace SamplePlugin.ReSharper
{
    [PsiComponent]
    public class SampleComponent
    {
        public void DoSomething(ISolution solution)
        {
#if RESHARPER
#elif RIDER
            var myRiderModel = solution.GetProtocolSolution().GetSampleModel();
#endif
        }
    }
}